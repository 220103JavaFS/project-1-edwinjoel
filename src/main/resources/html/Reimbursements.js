let url = 'http://3.23.112.168:7000/';
let url2 = 'http://localhost:7000/';

let table = document.getElementById('table');
let oldTableBody = document.getElementById('tbody');
let newTableBody = document.createElement('tbody');
newTableBody.id = 'tbody';

//Get All btn
let btn = document.getElementById('btn');
btn.addEventListener('click', getAll);

//Status dropdown selector
let statusEl = document.getElementById('status');

//Logout btn
let logoutbtn = document.getElementById('logoutbtn');
logoutbtn.addEventListener('click', logout);

//Author btn
let authorbtn = document.getElementById('authorbtn');
authorbtn.addEventListener('click', getByAuthor);

//Resolver btn
let resolverbtn = document.getElementById('resolverbtn');
resolverbtn.addEventListener('click', getByResolver);

getPending();

let a1 = document.getElementById('link1');
let a2 = document.getElementById('link2');

a1.href = `${url}MyReimbursement.html`;
a2.href = `${url}Reimbursements.html`;

let last = 'status';

async function refresh(last) {
  switch (last) {
    case 'status':
      getByStatus();
      break;
    case 'all':
      getAll();
      break;
    case 'author':
      getByAuthor();
      break;
    case 'resolver':
      getByResolver();
      break;
  }
}

async function getAll() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  console.log('entered fetchFunc');

  let response = await fetch(`${url}reimbursements`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    renderData(data);
    last = 'all';
  } else if (response.status === 403) {
    notManager();
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

  let statusValue = statusEl.value;

  let response = await fetch(`${url}reimbursements/status/${statusValue}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 201) {
    let data = await response.json();
    console.log(data);
    renderData(data);
    last = 'status';
  } else if (response.status === 403) {
    notManager();
  } else {
    console.log('Something went wrong');
  }
}

async function getByAuthor() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let authorId = document.getElementById('useridinput').value;

  let response = await fetch(`${url}reimbursements/author/${authorId}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    renderData(data);
    last = 'author';
  } else if (response.status === 204) {
    empty();
  } else if (response.status === 403) {
    notManager();
  } else {
    console.log('Something went wrong');
  }
}

async function getByResolver() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let resolverId = document.getElementById('useridinput').value;

  let response = await fetch(`${url}reimbursements/resolver/${resolverId}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    renderData(data);
    last = 'resolver';
  } else if (response.status === 204) {
    empty();
  } else if (response.status === 403) {
    notManager();
  } else {
    console.log('Something went wrong');
  }
}

async function empty() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let emptyText = document.createElement('p');
  emptyText.innerText = "There doesn't seem to be anything here for that...";
  newTableBody.appendChild(emptyText);

  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

async function notManager() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let emptyText = document.createElement('p');
  emptyText.innerText = "Hey are you sure you're suppose to be here...";
  newTableBody.appendChild(emptyText);

  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

async function getPending() {
  //get the new references everybutton press
  oldTableBody = document.getElementById('tbody');

  //create a new <tbody> element and set id="tbody"
  newTableBody = document.createElement('tbody');
  newTableBody.id = 'tbody';

  let statusValue = 'pending';

  let response = await fetch(`${url}reimbursements/status/${statusValue}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 201) {
    let data = await response.json();
    console.log(data);
    renderData(data);
  } else if (response.status === 403) {
    notManager();
  } else {
    console.log('Something went wrong');
  }
}

async function logout() {
  let response = await fetch(`${url}logout`, {
    method: 'GET',
    credentials: 'include',
  });

  if (response.status === 200) {
    console.log('logged out');
    window.location.href = url;
  } else {
    console.log('was not logged in');
    window.location.href = url;
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

    let changeStatus = document.createElement('td');
    let approveBtn = document.createElement('button');
    let denyBtn = document.createElement('button');

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
    newRow.appendChild(changeStatus);
    changeStatus.appendChild(approveBtn);
    changeStatus.appendChild(denyBtn);
    newRow.appendChild(d);
    d.appendChild(deleteBtn);

    id.innerText = reimb.id;
    amount.innerText = reimb.amount;
    submitted.innerText = new Date(reimb.timeSubmitted);
    if(reimb.timeResolved){
      resolved.innerText = new Date(reimb.timeResolved);
    }
    else{
      resolved.innerText = "";
    }
    
    description.innerText = reimb.description;
    authorId.innerText = reimb.authorUserId;
    resolverId.innerText = reimb.resolverUserId;
    type.innerText = reimb.type;
    status.innerText = reimb.status;
    approveBtn.innerText = '✓';
    approveBtn.setAttribute('class', 'btn btn-success');
    denyBtn.innerText = '✘';
    denyBtn.setAttribute('class', 'btn btn-warning');
    deleteBtn.innerText = '✘';
    deleteBtn.setAttribute('class', 'btn btn-danger');

    let authorTooltip = document.createElement('div');
    authorTooltip.setAttribute('class', 'mytooltiptext');
    let text = document.createElement('p');
    if (reimb.authorUser) {
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

    if (reimb.status == 'PENDING') {
      approveBtn.addEventListener('click', async (e) => {
        let response = await fetch(`${url}reimbursements/approve/${reimb.id}`, {
          method: 'PATCH',
          credentials: 'include',
        });
        if (response.status === 202) {
          console.log('Approved element');
          refresh(last);
        } else {
          console.log('approve failed');
        }
      });

      denyBtn.addEventListener('click', async (e) => {
        let response = await fetch(`${url}reimbursements/deny/${reimb.id}`, {
          method: 'PATCH',
          credentials: 'include',
        });
        if (response.status === 202) {
          console.log('Deny element');
          refresh(last);
        } else {
          console.log('Deny failed');
        }
      });
    } else {
      approveBtn.disabled = true;
      denyBtn.disabled = true;
    }

    deleteBtn.addEventListener('click', async (e) => {
      let response = await fetch(`${url}reimbursements/delete/${reimb.id}`, {
        method: 'DELETE',
        credentials: 'include',
      });
      if (response.status === 202) {
        console.log('deleted element');
        refresh(last);
      } else {
        console.log('delete failed');
      }
    });

    newTableBody.appendChild(newRow);
  }

  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}
