const SignUpbtn = document.getElementById("Signup-btn");
const SignInbtn = document.getElementById("Signin-btn");

const sign_up_btn = document.querySelector("#sign-up-btn");
const sign_in_btn = document.querySelector("#sign-in-btn");
const container = document.querySelector(".container");

////////
//animation
/////////
sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

/////////
//la function de post
/////////
function sendData(url, data) {
  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).then((response) => {
    if (!response.ok) {
      throw new Error("Erreur réseau");
    }
    return response.json();
  });
}

/////
//fonctionement
/////
SignUpbtn.addEventListener("click", (e) => {
  e.preventDefault();
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  console.log(name, email, password);

  // les champs ne sont pas tous remplis:
  if (!name || !email || !password) {
  }

  sendData("http://localhost:8080/signUp", {
    name: name,
    email: email,
    password: password,
  })
    .then((data) => {
      console.log(data);
      //pre-reset:
      document.querySelectorAll(".sign-up-form .input-field").forEach((e) => {
        e.classList.remove("wrong");
      });
      // les champs ne sont pas tous remplis:
      if (!name || !email || !password) {
        console.log(name, email, password);
        document.querySelectorAll(".sign-up-form .input-field").forEach((e) => {
          // console.log(e.querySelector("input").value);
          if (e.querySelector("input").value === "") {
            e.classList.add("wrong");
            console.log("added class wrong to ", e);
          }
        });
        console.log("not all filled");
        console.log(document.querySelector("#signup-error p "));
        //1. changer le message de la balise d'erreur
        document.querySelector("#signup-error p ").innerHTML =
          "<b>Missing fields</b> Please fill all of the form's fields.";
        document.querySelector("#signup-error").style.opacity = 1;
      } else {
        ////////////////
        //erreur côté backend :
        //////////////

        //reseting everything:
        //resetting color:
        document.querySelectorAll(".sign-up-form .input-field").forEach((e) => {
          e.classList.remove("wrong");
        });
        //resetting the displayed error message:
        document.querySelector("#signup-error p ").innerHTML =
          "<b>Email address already used.</b>Please try logging in with it or use another Email Address.";
        if (!data.result) {
          document.querySelector("#email-field").classList.add("wrong");
          document.querySelector("#signup-error").style.opacity = 1;
        } else {
          document
            .querySelectorAll(".sign-up-form .input-field")
            .forEach((e) => e.classList.remove("wrong"));
          document.querySelector("#signup-error").style.opacity = 0;
          window.location.href = "http://localhost:8080/welcome";
        }
      }
    })
    .catch((error) => {
      console.error("Erreur:", error);
    });
});

SignInbtn.addEventListener("click", (e) => {
  e.preventDefault();
  const email = document.getElementById("email1").value;
  const password = document.getElementById("pass").value;
  console.log(email, password);

  // TODO: refactoring errorDisplaying
  // const errorDisplaying = function (
  //   login,
  //   user,
  //   email,
  //   password,
  //   errorMessage
  // ) {
  //   if (login) {
  //     if(user){

  //     }
  //   }else{
  //   }
  // };

  sendData("http://localhost:8080/auth", {
    email: email,
    password: password,
  })
    .then((data) => {
      //resetting colors
      document
        .querySelectorAll(".sign-in-form .input-field")
        .forEach((e) => e.classList.remove("wrong"));
      //hiding the error message
      document.querySelector("#login-error").style.opacity = 0;
      console.log(data);
      if (email == "" && password == "") {
        //coloring with red the email field
        document.querySelector("#login-email").classList.add("wrong");
        document.querySelector("#login-password").classList.add("wrong");
        //changing the error message to say the correct error message
        document.querySelector("#login-error p ").innerHTML =
          "<b>Empty Fields</b> Please fill the Email & passwords fields and retry.";
        //displaying the error message on the page
        document.querySelector("#login-error").style.opacity = 1;
      } else if (email == "") {
        //coloring with red the email field
        document.querySelector("#login-email").classList.add("wrong");
        //changing the error message to say the correct error message
        document.querySelector("#login-error p ").innerHTML =
          "<b>Empty Field</b> Please fill the Email field and retry.";
        //displaying the error message on the page
        document.querySelector("#login-error").style.opacity = 1;
      } else if (password == "") {
        //coloring with red the password field
        document.querySelector("#login-password").classList.add("wrong");
        //changing the error message to say the correct error message
        document.querySelector("#login-error p ").innerHTML =
          "<b>Empty Field</b> Please fill the password field and retry.";
        //displaying the error message on the page
        document.querySelector("#login-error").style.opacity = 1;
        // first case: mail exists:
      } else if (data.mailExists && !data.result) {
        console.log("mail exists");
        //coloring with red the password field
        document.querySelector("#login-password").classList.add("wrong");
        //changing the error message to say the correct error message
        document.querySelector("#login-error p").innerHTML =
          "<b>Invalid Password</b> Please re-enter your password and try again.";
        //displaying the error message on the page
        document.querySelector("#login-error").style.opacity = 1;
      } else if (!data.mailExists && !data.result) {
        //coloring with red the email field
        document.querySelector("#login-email").classList.add("wrong");
        //changing the error message to say the correct error message
        document.querySelector("#login-error p ").innerHTML =
          "<b>Invalid Email</b>An Account with the Email you introduced doesn't exist";
        //displaying the error message on the page
        document.querySelector("#login-error").style.opacity = 1;
      } else if (data.result) {
        console.log("here");
        //redirecting to the dashboard
        window.location.href = "http://localhost:8080/dashboard";
      }
    })
    .catch((error) => {
      console.error("Erreur:", error);
    });
});
