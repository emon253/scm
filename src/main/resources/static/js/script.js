// User Dashboard

const toggleSideBar = () => {
    if ($('.side-bar').is(":visible")) {
        $(".side-bar").css("display", "none");
        $(".content").css("margin-left", "0%")




    } else {
        $(".side-bar").css("display", "block");
        $(".content").css("margin-left", "20%")
    }
};