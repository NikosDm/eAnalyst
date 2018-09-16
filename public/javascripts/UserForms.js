$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $("#RegisterUsername").val('');
        $("#RegisterPassword").val('');
        $("#RegisterEmail").val('');
        $("#RegisterFirstName").val('');
        $("#RegisterLastName").val('');
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $("#LoginUsername").val('');
        $("#LoginPassword").val('');
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});
