/**
* Template Name: Lonely - v2.0.0
* Template URL: https://bootstrapmade.com/free-html-bootstrap-template-lonely/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/
!(function($) {
  "use strict";

  $('.project-button').mouseover(function(){
	  $(this).css("background-color","#80ffff");
	  
  })
  $('.project-button').mouseout(function(){
	  $(this).css("background-color","white");
	  
  })
  
  // Smooth scroll for the navigation menu and links with .scrollto classes
  $(document).on('click', '.nav-menu a, .mobile-nav a, .scrollto', function(e) {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      e.preventDefault();
      var target = $(this.hash);
      if (target.length) {
        var scrollto = target.offset().top;
        var scrolled = 20;
        if ($('#header').length) {
          scrollto -= $('#header').outerHeight()
          if (!$('#header').hasClass('header-scrolled')) {
            scrollto += scrolled;
          }
        }
        if ($(this).attr("href") == '#header') {
          scrollto = 0;
        }
        $('html, body').animate({
          scrollTop: scrollto
        }, 1500, 'easeInOutExpo');
        if ($(this).parents('.nav-menu, .mobile-nav').length) {
          $('.nav-menu .active, .mobile-nav .active').removeClass('active');
          $(this).closest('li').addClass('active');
        }
        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
          $('.mobile-nav-overly').fadeOut();
        }
        return false;
      }
    }
  });

  // Mobile Navigation
  if ($('.nav-menu').length) {
    var $mobile_nav = $('.nav-menu').clone().prop({
      class: 'mobile-nav d-lg-none'
    });
    $('body').append($mobile_nav);
    $('body').prepend('<button type="button" class="mobile-nav-toggle d-lg-none"><i class="icofont-navigation-menu"></i></button>');
    $('body').append('<div class="mobile-nav-overly"></div>');
    $(document).on('click', '.mobile-nav-toggle', function(e) {
      $('body').toggleClass('mobile-nav-active');
      $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
      $('.mobile-nav-overly').toggle();
    });
    $(document).on('click', '.mobile-nav .drop-down > a', function(e) {
      e.preventDefault();
      $(this).next().slideToggle(300);
      $(this).parent().toggleClass('active');
    });
    $(document).click(function(e) {
      var container = $(".mobile-nav, .mobile-nav-toggle");
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
          $('.mobile-nav-overly').fadeOut();
        }
      }
    });
  } else if ($(".mobile-nav, .mobile-nav-toggle").length) {
    $(".mobile-nav, .mobile-nav-toggle").hide();
  }

  // Stick the header at top on scroll
  $("#header").sticky({
    topSpacing: 0,
    zIndex: '50'
  });

  // Back to top button
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top').fadeIn('slow');
    } else {
      $('.back-to-top').fadeOut('slow');
    }
  });
  $('.back-to-top').click(function() {
    $('html, body').animate({
      scrollTop: 0
    }, 1500, 'easeInOutExpo');
    return false;
  });

  // jQuery counterUp
  $('[data-toggle="counter-up"]').counterUp({
    delay: 10,
    time: 1000
  });

  // Skills section
  $('.skills-content').waypoint(function() {
    $('.progress .progress-bar').each(function() {
      $(this).css("width", $(this).attr("aria-valuenow") + '%');
    });
  }, {
    offset: '80%'
  });

  // Porfolio isotope and filter
  $(window).on('load', function() {
    var portfolioIsotope = $('.portfolio-container').isotope({
      itemSelector: '.portfolio-item',
      layoutMode: 'fitRows'
    });
    $('#portfolio-flters li').on('click', function() {
      $("#portfolio-flters li").removeClass('filter-active');
      $(this).addClass('filter-active');
      portfolioIsotope.isotope({
        filter: $(this).data('filter')
      });
    });
    // Initiate venobox (lightbox feature used in portofilo)
    $(document).ready(function() {
      $('.venobox').venobox();
    });
  });

  // Testimonials carousel (uses the Owl Carousel library)
  $(".testimonials-carousel").owlCarousel({
    autoplay: true,
    dots: true,
    loop: true,
    responsive: {
      0: {
        items: 1
      },
      768: {
        items: 2
      },
      900: {
        items: 3
      }
    }
  });
  
  //글씨 써지는거 시작
  var typingBool = false; 
  var typingIdx = 0; 
  var liIndex = 0;
  var liLength = $(".typing-txt>h1").length;
  // 타이핑될 텍스트를 가져온다 
  var typingTxt = $(".typing-txt>h1").eq(liIndex).text(); 
  //liIndex 인덱스로 구분해 한줄씩 가져옴

  typingTxt=typingTxt.split(""); // 한글자씩 잘라 배열로만든다

  if(typingBool==false){ // 타이핑이 진행되지 않았다면 
      typingBool=true; 
      var tyInt = setInterval(typing,100); // 반복동작 
  }
       
  function typing(){
    $(".typing h1").removeClass("on");
     $(".typing h1").eq(liIndex).addClass("on");
     
    //현재 타이핑되는 문장에만 커서 애니메이션을 넣어준다.
    
    if(typingIdx<typingTxt.length){ // 타이핑될 텍스트 길이만큼 반복 
       $(".typing h1").eq(liIndex).append(typingTxt[typingIdx]); // 한글자씩 이어준다. 
       typingIdx++; 
     } else{ //한문장이끝나면
       if(liIndex<liLength-1){
       //다음문장으로  가기위해 인덱스를 1증가
         liIndex++; 
       //다음문장을 타이핑하기위한 셋팅
          typingIdx=0;
          typingBool = false;
          typingTxt = $(".typing-txt>h1").eq(liIndex).text();
         
       //다음문장 타이핑전 1초 쉰다
           clearInterval(tyInt);
            //타이핑종료
       
           setTimeout(function(){
             //1초후에 다시 타이핑 반복 시작
             tyInt = setInterval(typing,100);
           },1000);
          } else if(liIndex==liLength-1){
            
            //마지막 문장까지 써지면 반복종료
            clearInterval(tyInt);
            
            //1초후
            setTimeout(function(){
  	          //사용했던 변수 초기화
  	          typingBool = false; 
  	          liIndex=0;
  	          typingIdx=-0;
  	            
  	          //첫번째 문장으로 셋팅
  	          typingTxt = $(".typing-txt>h1").eq(liIndex).text(); 
  	               //타이핑 결과 모두 지우기
  	          $(".typing h1").html("")
  	            
  	          //반복시작
  	          tyInt = setInterval(typing,100);
            }, 1000);
          }
      }
  }  
})(jQuery);