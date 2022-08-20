package com.monott.user;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Clova_CAPTCHA {
	String clientId="wmgrh8n163";
	String clientSecret = "zbdf2JuwBc9Sc9RIovD4RAq5bT9jkz4PcCs62Bny";
	String key;
	
	@GetMapping("/captchaform")
	  public String captcharform() {
		  return "captcha_img";
	  }
	  
	  @GetMapping("/captcha")
	  public void reqCaptcha(  HttpServletResponse res   ) {
		  try {
		        // 1단계 캡차키를 발급 요청하여 발급받습니다.
		        key = getCode();
			    
		        // 2단계 발급받은 캡차키를 이용해 캡차 이미지를 요청하여 발급받습니다.
		        String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
		        URL url  = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("GET");
		        int responsecode =con.getResponseCode();
		        BufferedReader br;
		        if( responsecode == 200 ) {
		        	InputStream is = con.getInputStream();
		        	int read=0;
		        	byte[] bytes = new byte[1024];
		        	OutputStream outputStream = res.getOutputStream();
		        	while( ( read = is.read(bytes) ) != -1 ) {
		        		outputStream.write(bytes, 0, read);
		        	}
		        	is.close();
		       }else {
		    	     br = new BufferedReader( new InputStreamReader( con.getErrorStream() ) );  
		    	     String inputLine;
		    	     StringBuffer response = new StringBuffer();
		    	     while(  (inputLine = br.readLine() ) != null ) {
		    	    	 response.append(inputLine);
		    	     }
		    	     br.close();
		    	     System.out.println("====> error일 경우 나오는 곳 " + response.toString());
		       }
		  }catch(Exception e) {e.printStackTrace(); }
	  }
	 
	  //3단계 사용자가 이미지를 보고 입력한 값을 캡차키와 비교합니다.
	  @PostMapping("/captchacheck")
	  public String captchaCheck(@RequestParam("userin") String userin, Model model ) {
		  boolean result = false;
		  String view="";
		  try {
			 String code="1";
			 String value= userin;
			 String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" +  code + "&key=" + key + "&value=" + value;
			 URL url = new URL(apiURL);
			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			 con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			 int responsecode = con.getResponseCode();
			 BufferedReader br;
			 if( responsecode == 200 ) {
				 br = new BufferedReader(new InputStreamReader( con.getInputStream() ) );
			 } else {
				 br = new BufferedReader(new InputStreamReader( con.getErrorStream() ) );
			 }
			 
			 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while(  ( inputLine  = br.readLine() ) != null ) {
				  response.append(inputLine);
			 }
			 br.close();
			 
			 JSONObject jsonobj = new JSONObject(response.toString());
			 result = (boolean) jsonobj.get("result");
			 if(result) {
				 model.addAttribute("result", "login success");
				 view="captcha_check";
			 }else {
				 view = "redirect:/captchaform";
			 }
			 System.out.println("=======> 3단계완료 됨 " + response.toString() ); 
			 
		  }catch(Exception e) {e.printStackTrace();}
		  
		 return view;
		 
	  }
	//1단계 처리    캡차키를 발급 요청하여 발급받습니다.
	private String getCode() {
		String key="";
		try {
			String code="0";
			String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code="+code;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if( responseCode == 200 ) {
				 br = new BufferedReader( new InputStreamReader( con.getInputStream() ) ); 
			} else {
				 br = new BufferedReader( new InputStreamReader( con.getErrorStream() ) ); 
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while( (  inputLine = br.readLine()  ) != null  ) {
				response.append(inputLine);
			}
			br.close();
			JSONObject jsonobj = new JSONObject(response.toString());
			key = (String) jsonobj.get("key");
			System.out.println("======>  key : " +  key + " response value ==>  " + response.toString());
		}catch(Exception e) {e.printStackTrace();}
		
		return key;
	}

}