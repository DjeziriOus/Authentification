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

  sendData("http://localhost:8081/signUp", {
    name: name,
    email: email,
    password: password,
  })
    .then((data) => {
      console.log(data);
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

  sendData("http://localhost:8081/auth", { email: email, password: password })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error("Erreur:", error);
    });
});
