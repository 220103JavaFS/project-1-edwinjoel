//Login btn
let registerbtn = document.getElementById('registerbtn');
registerbtn.addEventListener('click', register);
let url = 'http://localhost:7000/users/new';

async function register() {
  let firstnameEl = document.getElementById('firstname');
  let lastnameEl = document.getElementById('lastname');
  let usernameEl = document.getElementById('username');
  let passwordEl = document.getElementById('password');
  let emailEl = document.getElementById('email');
  let roleEl = document.getElementById('role');
  let user = {
    firstName: firstnameEl.value,
    lastName: lastnameEl.value,
    username: usernameEl.value,
    password: passwordEl.value,
    email: emailEl.value,
    userRoleString: roleEl.value,
  };

  let response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(user),
  });

  if (response.status === 201) {
    console.log('Registered');
    window.location.href = 'http://localhost:7000';
  } else {
    console.log('Register Failed');
    alert("Register Failed, but uhh... we're sure you'll figure it out...");
  }
}
