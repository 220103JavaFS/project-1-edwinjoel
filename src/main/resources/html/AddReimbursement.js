let addReimbursementBtn = document.getElementById("addBtn");

const timeSubmitted = Date.now();
const url = "http://localhost:7000/reimbursements/add"

addBtn.addEventListener("click", submitTicket);

async function submitReimbursement(){
  let Reimbursement = {
    reimb_amount: document.getElementById("amount").value,
    reimb_description:document.getElementById("description").value,
    reimb_submitted:timeSubmitted,
    reimb_status_id: 1,
    reimb_type_id: document.getElementById("type").value,
	authorUserId: userId
  }

  let response = await fetch(url, {
    method:"POST",
    body:JSON.stringify(Reimbursement),
    credentials:"include"
  })
}

