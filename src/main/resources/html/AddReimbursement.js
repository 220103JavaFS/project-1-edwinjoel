const url = 'http://localhost:7000/reimbursements/add';
let table = document.getElementById('table');
let oldTableBody = document.getElementById('tbody');
let newTableBody = document.createElement('tbody');
newTableBody.id = 'tbody';

let addBtn = document.getElementById('addBtn');
addBtn.addEventListener('click', submitReimbursement);

//Login btn
let loginbtn = document.getElementById('loginbtn');
loginbtn.addEventListener('click', login);

//Logout btn
let logoutbtn = document.getElementById('logoutbtn');
logoutbtn.addEventListener('click', logout);

//Refresh btn
let refreshbtn = document.getElementById('refreshbtn');
refreshbtn.addEventListener('click', refresh);

async function submitReimbursement() {
  let Reimbursement = {
    amount: document.getElementById('amount').value,
    description: document.getElementById('description').value,
    typeId: document.getElementById('type').value,
  };

  let response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(Reimbursement),
    credentials: 'include',
  });

  if (response.status === 202) {
    console.log('Added to db');
    refresh();
  } else {
    console.log('Failed to add to db');
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
    refresh();
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
    renderData([]);
  } else {
    console.log('logout failed');
  }
}

async function refresh() {
  let url = 'http://localhost:7000/reimbursements/myreimbursements';

  let response = await fetch(url, { method: 'GET', credentials: 'include' });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    renderData(data);
  } else if (response.status == 204) {
    console.log("You don't have any reimbursements");
  } else {
    console.log('Something went wrong');
  }
}

function renderData(data) {
  table = document.getElementById('table');
  oldTableBody = document.getElementById('tbody');
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

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

    let authorTooltip = document.createElement('div');
    authorTooltip.setAttribute('class', 'mytooltiptext');
    let text = document.createElement('p');
    if (reimb.authorUser.username) {
      text.innerText = `${reimb.authorUser.firstName} ${reimb.authorUser.lastName}
      ${reimb.authorUser.role} ${reimb.authorUser.username}
      ${reimb.authorUser.email}`;
      authorTooltip.appendChild(text);
      authorId.appendChild(authorTooltip);
      authorId.setAttribute('class', 'mytooltip');
    }

    let resolverTooltip = document.createElement('div');
    resolverTooltip.setAttribute('class', 'mytooltiptext');
    let text2 = document.createElement('p');
    if (reimb.resolverUser.username) {
      text2.innerText = `${reimb.resolverUser.firstName} ${reimb.resolverUser.lastName}
      ${reimb.resolverUser.role} ${reimb.resolverUser.username}
      ${reimb.resolverUser.email}`;
      resolverTooltip.appendChild(text2);
      resolverId.appendChild(resolverTooltip);
      resolverId.setAttribute('class', 'mytooltip');
    }

    newTableBody.appendChild(newRow);
  }

  table.replaceChild(newTableBody, oldTableBody);
}
