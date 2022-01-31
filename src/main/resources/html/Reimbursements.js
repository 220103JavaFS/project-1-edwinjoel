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
  } else {
    console.log('log in failed ');
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

    newRow.appendChild(id);
    newRow.appendChild(amount);
    newRow.appendChild(submitted);
    newRow.appendChild(resolved);
    newRow.appendChild(description);
    newRow.appendChild(authorId);
    newRow.appendChild(resolverId);
    newRow.appendChild(type);
    newRow.appendChild(status);

    id.innerText = reimb.id;
    amount.innerText = reimb.amount;
    submitted.innerText = new Date(reimb.timeSubmitted);
    resolved.innerText = new Date(reimb.timeResolved);
    description.innerText = reimb.description;
    authorId.innerText = reimb.authorUserId;
    resolverId.innerText = reimb.resolverUserId;
    type.innerText = reimb.type;
    status.innerText = reimb.status;

    newTableBody.appendChild(newRow);
  }

  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}
