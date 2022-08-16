$(function () {

    $(".modalbtn").click(function () {
      $(".modal").fadeIn();
    });



    $(document).keydown(function (e) {
      //keyCode 구 브라우저, which 현재 브라우저
      var code = e.keyCode || e.which;

      if (code == 27) { // 27은 ESC 키번호
        $('.modal').hide();
      }
    });

  });
  $(function () {
    $(".cancel").click(function () {
      $(".modal").fadeOut();
    })
  })
  $(function () {
    $(".logo_home").click(function () {
      $(".modal").fadeOut();
    })
  })
