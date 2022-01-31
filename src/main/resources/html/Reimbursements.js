let table = document.getElementById("table");
let btn = document.getElementById("btn");
btn.addEventListener("click", fetchFunc);

async function fetchFunc(){
    console.log("entered fetchFunc")
    let url = 'http://localhost:7000/reimbursements'
    
    let response = await fetch(url, {method:'GET'})

    if(response.status === 200){
        let data = await response.json()
        console.log(data)
        renderData(data)
    }
    else{
        console.log("Something went wrong")
    }
}

function renderData(data){

    for(let reimb of data){
        let newRow = document.createElement("tr")
        let id = document.createElement("td")
        let amount = document.createElement("td")
        let submitted = document.createElement("td")
        let resolved = document.createElement("td")
        let description = document.createElement("td")
        let authorId = document.createElement("td")
        let resolverId = document.createElement("td")
        let type = document.createElement("td")
        let status = document.createElement("td")
    
        newRow.appendChild(id)
        newRow.appendChild(amount)
        newRow.appendChild(submitted)
        newRow.appendChild(resolved)
        newRow.appendChild(description)
        newRow.appendChild(authorId)
        newRow.appendChild(resolverId)
        newRow.appendChild(type)
        newRow.appendChild(status)

        id.innerText = reimb.id
        amount.innerText = reimb.amount
        submitted.innerText = reimb.timeSubmitted
        resolved.innerText = reimb.timeResolved
        description.innerText = reimb.description
        authorId.innerText = reimb.authorUserId
        resolverId.innerText = reimb.resolverUserId
        type.innerText = reimb.type
        status.innerText = reimb.status

        table.appendChild(newRow)

    }
    




}



