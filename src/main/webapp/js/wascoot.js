var body = $('body');
var html = $('html');

/////////////
var wascootjs = function(){
    "use strict"
    var screenWidth = $( window ).width();
    var screenHeight = $( window ).height();

    var handleSelectPicker = function(){
        if(jQuery('.default-select,.table-responsive select').length > 0 ){
            jQuery('.default-select,.table-responsive select').selectpicker();
        }
    }

    var handlePreloader = function(){
        setTimeout(function() {
            jQuery('#preloader').remove();
            $('#main-wrapper').addClass('show');
        },800);
    }

    var handleMetisMenu = function() {
        if(jQuery('#menu').length > 0 ){
            $("#menu").metisMenu();
        }
    }

    var handleNavigation = function() {
        $(".nav-control").on('click', function() {

            $('#main-wrapper').toggleClass("menu-toggle");

            $(".hamburger").toggleClass("is-active");
        });
    }

    var handleCurrentActive = function() {
        for (var nk = window.location,
                 o = $("ul#menu a").filter(function() {

                     return this.href == nk;

                 })
                     .addClass("mm-active")
                     .parent()
                     .addClass("mm-active");;)
        {

            if (!o.is("li")) break;

            o = o.parent()
                .addClass("mm-show")
                .parent()
                .addClass("mm-active");
        }
    }

    var handleMiniSidebar = function() {
        $("ul#menu>li").on('click', function() {
            const sidebarStyle = $('body').attr('data-sidebar-style');
            if (sidebarStyle === 'mini') {
                console.log($(this).find('ul'))
                $(this).find('ul').stop()
            }
        })
    }

    var handleMenuTabs = function() {
        if(screenWidth <= 991 ){
            jQuery('.menu-tabs .nav-link').on('click',function(){
                if(jQuery(this).hasClass('open'))
                {
                    jQuery(this).removeClass('open');
                    jQuery('.fixed-content-box').removeClass('active');
                    jQuery('.hamburger').show();
                }else{
                    jQuery('.menu-tabs .nav-link').removeClass('open');
                    jQuery(this).addClass('open');
                    jQuery('.fixed-content-box').addClass('active');
                    jQuery('.hamburger').hide();
                }
            });
            jQuery('.close-fixed-content').on('click',function(){
                jQuery('.fixed-content-box').removeClass('active');
                jQuery('.hamburger').removeClass('is-active');
                jQuery('#main-wrapper').removeClass('menu-toggle');
                jQuery('.hamburger').show();
            });
        }
    }
    /* Header Fixed ============ */
    var headerFix = function(){
        'use strict';
        /* Main navigation fixed on top  when scroll down function custom */
        jQuery(window).on('scroll', function () {

            if(jQuery('.header').length > 0){
                var menu = jQuery('.header');
                $(window).scroll(function(){
                    var sticky = $('.header'),
                        scroll = $(window).scrollTop();

                    if (scroll >= 100){ sticky.addClass('is-fixed');
                    }else {sticky.removeClass('is-fixed');}
                });
            }

        });
        /* Main navigation fixed on top  when scroll down function custom end*/
    }

    var handleMenuPosition = function(){

        if(screenWidth > 1024){
            $(".metismenu  li").unbind().each(function (e) {
                if ($('ul', this).length > 0) {
                    var elm = $('ul:first', this).css('display','block');
                    var off = elm.offset();
                    var l = off.left;
                    var w = elm.width();
                    var elm = $('ul:first', this).removeAttr('style');
                    var docH = $("body").height();
                    var docW = $("body").width();
                }
            });
        }
    }
    /* Handle Page On Scroll ============ */
    var handlePageOnScroll = function(event){

        'use strict';
        var headerHeight = parseInt($('.header').css('height'), 10);

        $('.navbar-nav .scroll').on('click', function(event)
        {
            event.preventDefault();

            jQuery('.navbar-nav .scroll').parent().removeClass('active');
            jQuery(this).parent().addClass('active');

            if (this.hash !== "") {
                var hash = this.hash;
                var seactionPosition = parseInt($(hash).offset().top, 10);
                var headerHeight =   parseInt($('.header').css('height'), 10);

                var scrollTopPosition = seactionPosition - headerHeight;
                $('html, body').animate({
                    scrollTop: scrollTopPosition
                }, 800, function(){

                });
            }
        });

        pageOnScroll();
    }

    /* Page On Scroll ============ */
    var pageOnScroll = function(event){

        if(jQuery('.navbar-nav').length > 0){

            var headerHeight = parseInt(jQuery('.header').height(), 10);

            jQuery(document).on("scroll", function(){

                var scrollPos = jQuery(this).scrollTop();
                jQuery('.navbar-nav .scroll').each(function () {
                    var elementLink = jQuery(this);

                    var refElement = jQuery(elementLink.attr("href"));

                    if(jQuery(this.hash).offset() != undefined){
                        var seactionPosition = parseInt(jQuery(this.hash).offset().top, 10);
                    }else{
                        var seactionPosition = 0;
                    }
                    var scrollTopPosition = (seactionPosition - headerHeight);

                    if (scrollTopPosition <= scrollPos){
                        elementLink.parent().addClass("active");
                        elementLink.parent().siblings().removeClass("active");
                    }
                });

            });
        }
    }

    /* Function ============ */
    return {
        init:function(){
            handleMetisMenu();
            handleNavigation();
            handleCurrentActive();
            handleMiniSidebar();
            handleMenuTabs();
            headerFix();
            handleSelectPicker();
            handlePageOnScroll();
        },
        load:function(){
            handlePreloader();
        },
        handleMenuPosition:function(){

            handleMenuPosition();
        },
    }

}();

/* Document.ready Start */
jQuery(document).ready(function() {
    'use strict';
    wascootjs.init();

});
/* Document.ready END */

/* Window Load START */
jQuery(window).on('load',function () {
    'use strict';
    wascootjs.load();
    setTimeout(function(){
        wascootjs.handleMenuPosition();
    }, 1000);

});
/*  Window Load END */
/* Window Resize START */
jQuery(window).on('resize',function () {
    'use strict';
    wascootjs.resize();
    setTimeout(function(){
        wascootjs.handleMenuPosition();
    }, 1000);
});
/*  Window Resize END */

//////