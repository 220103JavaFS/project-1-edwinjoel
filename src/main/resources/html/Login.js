//Login btn
let loginbtn = document.getElementById('loginbtn');
loginbtn.addEventListener('click', login);
let url = 'http://localhost:7000/login';

async function login() {
  let usernameEl = document.getElementById('username');
  let passwordEl = document.getElementById('password');
  let user = {
    username: usernameEl.value,
    password: passwordEl.value,
  };

  let response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(user),
    credentials: 'include',
  });

  if (response.status === 200) {
    console.log('logged in');
  } else {
    console.log('log in failed ');
  }
}