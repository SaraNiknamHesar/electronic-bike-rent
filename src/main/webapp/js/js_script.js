$(document).ready(function () {
  console.log("function");
  var ADMIN_LIST;
  var url = "../json/administrators.json";
  console.log("function");
  $.get(url).done(function (response) {
    const data = response;
    let items = data;
    items = items.splice(0, 7);
    ADMIN_LIST = items.map((it) => it);
    console.log("items", ADMIN_LIST);

    var select_films = $("#tv_select");
    ADMIN_LIST.map((admin) => {
      var opt = $("<option>").attr("value", admin.title);
      opt.text(admin.title);
      select_films.append(opt);
    });
  });


  $("#infoAccount").click(function () {
    console.log("clicked");
    $("#infoAdmin").modal("show");
  });

  $("#updateAdminPassword").click(function () {
    console.log("clicked");
    $("#changeAccountPassword").modal("show");
  });

  var confirm_button = $("#confirm");
  confirm_button.on("click", function () {
    var title_selected = $("#tv_select").val();
    console.log(title_selected);
    generate_instance_movie(title_selected);
  });

  function generate_instance_movie(admin_title) {
    // get the movie in json (also retrievable with a ajax call)
    console.log("click");
    var admin_json = ADMIN_LIST.filter((x) => x.title === admin_title)[0];
    var adminId = admin_json.admin_id;
    var adminEmail = admin_json.email;

    var film_container = $("#film_container");
    console.log(film_container);

    var container = $("<div>");

    var img = $("<img>");
    img.attr("src", "../media/adminPhoto/" + admin_json["id"].toString() + ".png");
    img.attr("id", "img_" + admin_json["id"]);
    img.addClass("image-lst");

    var plot_el = $("<div>");
    var plot_title = $("<h5>");
    plot_title.text("Admin");
    plot_el.text(admin_title);
    plot_el.css("width", "100%");
    container.addClass("container_film_class");
    var hr = $("<hr/>");
    var Email = $("<h5>");
    Email.text("Email");
    var email = $("<div>");
    email.text(adminEmail);

    var Id = $("<h5>");


    container.append(img);
    container.append(Email);
    container.append(email);

    container.append(hr);

    container.append(plot_title);
    container.append(plot_el);
    film_container.append(container);
  }


  var remove_button = $("#reset");
    remove_button.on("click", function () {
    remove_children();

  });
  function remove_children() {
    const film_container = document.getElementById("film_container");
    film_container.removeChild(film_container.firstChild);
  }
});
