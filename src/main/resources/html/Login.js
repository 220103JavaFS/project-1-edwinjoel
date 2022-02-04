//Login btn
let loginbtn = document.getElementById('loginbtn');
loginbtn.addEventListener('click', login);
let url = 'http://3.23.112.168:7000/';
let url2 = 'http://localhost:7000/';

async function login() {
  let usernameEl = document.getElementById('username');
  let passwordEl = document.getElementById('password');
  let user = {
    username: usernameEl.value,
    password: passwordEl.value,
  };

  let response = await fetch(`${url}login`, {
    method: 'POST',
    body: JSON.stringify(user),
    credentials: 'include',
  });

  if (response.status === 200) {
    console.log('logged in');
    window.location.href = `${url}MyReimbursement.html`;
  } else {
    console.log('log in failed ');
    alert('Invaild username or password');
  }
}
