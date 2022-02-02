let table = document.getElementById('table');
let oldTableBody = document.getElementById('tbody');
let newTableBody = document.createElement('tbody');
newTableBody.id = 'tbody';

//Get All btn
let btn = document.getElementById('btn');
btn.addEventListener('click', fetchFunc);

//Get by status btn
let statusbtn = document.getElementById('statusbtn');
statusbtn.addEventListener('click', getByStatus);

//Login btn
let loginbtn = document.getElementById('loginbtn');
loginbtn.addEventListener('click', login);

let logoutbtn = document.getElementById('logoutbtn');
logoutbtn.addEventListener('click', logout);

async function fetchFunc() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  console.log('entered fetchFunc');
  let url = 'http://localhost:7000/reimbursements';

  let response = await fetch(url, { method: 'GET', credentials: 'include' });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    renderData(data);
  } else {
    console.log('Something went wrong');
  }
}

async function getByStatus() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let statusEl = document.getElementById('status');
  let statusValue = statusEl.value;

  let url = `http://localhost:7000/reimbursements/status/${statusValue}`;

  let response = await fetch(url, { method: 'GET', credentials: 'include' });

  if (response.status === 201) {
    let data = await response.json();
    console.log(data);
    renderData(data);
  } else {
    console.log('Something went wrong');
  }
}

async function login() {
  let usernameEl = document.getElementById('username');
  let passwordEl = document.getElementById('password');
  let usernamelabelEl = document.getElementById('usernamelabel');
  let passwordlabelEl = document.getElementById('passwordlabel');
  let user = {
    username: usernameEl.value,
    password: passwordEl.value,
  };

  let url = 'http://localhost:7000/login';

  let response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(user),
    credentials: 'include',
  });

  if (response.status === 200) {
    console.log('logged in');
    usernameEl.hidden = true;
    passwordEl.hidden = true;
    loginbtn.hidden = true;
    usernamelabelEl.hidden = true;
    passwordlabelEl.hidden = true;
    logoutbtn.hidden = false;
  } else {
    console.log('log in failed ');
  }
}

async function logout() {
  let usernameEl = document.getElementById('username');
  let passwordEl = document.getElementById('password');
  let usernamelabelEl = document.getElementById('usernamelabel');
  let passwordlabelEl = document.getElementById('passwordlabel');
  let url = 'http://localhost:7000/logout';

  let response = await fetch(url, { method: 'GET', credentials: 'include' });

  if (response.status === 200) {
    console.log('logged out');
    usernameEl.hidden = false;
    passwordEl.hidden = false;
    loginbtn.hidden = false;
    usernamelabelEl.hidden = false;
    passwordlabelEl.hidden = false;
    logoutbtn.hidden = true;
  } else {
    console.log('logout failed');
  }
}

function renderData(data) {
  for (let reimb of data) {
    let newRow = document.createElement('tr');

    let id = document.createElement('td');
    let amount = document.createElement('td');
    let submitted = document.createElement('td');
    let resolved = document.createElement('td');
    let description = document.createElement('td');
    let authorId = document.createElement('td');
    let resolverId = document.createElement('td');
    let type = document.createElement('td');
    let status = document.createElement('td');

    let d = document.createElement('td');
    let deleteBtn = document.createElement('button');

    newRow.appendChild(id);
    newRow.appendChild(amount);
    newRow.appendChild(submitted);
    newRow.appendChild(resolved);
    newRow.appendChild(description);
    newRow.appendChild(authorId);
    newRow.appendChild(resolverId);
    newRow.appendChild(type);
    newRow.appendChild(status);
    newRow.appendChild(d);

    id.innerText = reimb.id;
    amount.innerText = reimb.amount;
    submitted.innerText = new Date(reimb.timeSubmitted);
    resolved.innerText = new Date(reimb.timeResolved);
    description.innerText = reimb.description;
    authorId.innerText = reimb.authorUserId;
    resolverId.innerText = reimb.resolverUserId;
    type.innerText = reimb.type;
    status.innerText = reimb.status;
    deleteBtn.innerText = 'X';

    let authorTooltip = document.createElement('div');
    authorTooltip.setAttribute('class', 'tooltiptext');
    let text = document.createElement('p');
    if (reimb.authorUser) {
      text.innerText = `${reimb.authorUser.firstName} ${reimb.authorUser.lastName}
      ${reimb.authorUser.role} ${reimb.authorUser.username}
      ${reimb.authorUser.email}`;
      authorTooltip.appendChild(text);
      authorId.appendChild(authorTooltip);
      authorId.setAttribute('class', 'tooltip');
    }

    let resolverTooltip = document.createElement('div');
    resolverTooltip.setAttribute('class', 'tooltiptext');
    let text2 = document.createElement('p');
    if (reimb.resolverUser.username) {
      text2.innerText = `${reimb.resolverUser.firstName} ${reimb.resolverUser.lastName}
      ${reimb.resolverUser.role} ${reimb.resolverUser.username}
      ${reimb.resolverUser.email}`;
      resolverTooltip.appendChild(text2);
      resolverId.appendChild(resolverTooltip);
      resolverId.setAttribute('class', 'tooltip');
    }

    deleteBtn.addEventListener('click', async (e) => {
      let url = `http://localhost:7000/reimbursements/delete/${reimb.id}`;
      let response = await fetch(url, {
        method: 'DELETE',
        credentials: 'include',
      });
      if (response.status === 202) {
        console.log('deleted element');
      } else {
        console.log('delete failed');
      }
    });
    d.appendChild(deleteBtn);

    resolverId.setAttribute('class', 'tooltip');

    newTableBody.appendChild(newRow);
  }

  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}
