let getProjectPerPage = (page) => {
    let data = null
    let search = document.getElementById('my-search')
    let url = ''
    if (search !== null && search.value !== ''){
        console.log(search.value)
        myrecover = search.value;
        url = 'http://localhost:8080/api/wiki/all/search/'+ myrecover +'?pageSize=4&pageNumber=' + page
    }
    else {
        if (page === 0) {
            url = 'http://localhost:8080/api/wiki/all/bypage?pageSize=4&pageNumber=0'
        } else {
            url = 'http://localhost:8080/api/wiki/all/bypage?pageSize=4&pageNumber=' + page
        }
    }
        
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            let response = data.content
            console.log(response);
            let page_total = data.totalPages
            let projet_total = data.totalElements
            let tbody = document.getElementById('tab_body')
            tbody.innerHTML = ''
            let tr, td, text
            let array = ['title', 'description']
            response.forEach(elt => {
                tr = document.createElement('tr')
                tbody.appendChild(tr)
                for (let index = 0; index < 2; index++) {
                    td = document.createElement('td')
                    if (index == 2)
                        text = (elt.tag_list.length == 0) ? document.createTextNode('None') : document.createTextNode(elt[array[index]].join(' - '))
                    else
                        text = document.createTextNode(elt[array[index]])
                    tr.appendChild(td)
                    td.appendChild(text)
                }
            });
            createPagButton(page_total, page)
        })
        .catch(error => console.log(error))
}

let createPagButton = (page_total , current_page) => {
    let pagButton = document.getElementById('pag_button')
    pagButton.innerHTML = ''

    // Ajout du bouton "First"
    pagButton.innerHTML += '<button type="button" class="page-btn" onclick="setPage(0)">First</button>&nbsp;&nbsp;'

    let start = Math.max(1, current_page)
    let end = Math.min(page_total, start + 2)
    for (let index = start - 1; index < end; index++) {
        let btnClass = (index === current_page) ? 'page-btn-current-page' : 'page-btn';
        pagButton.innerHTML += '<button type="button" class="' + btnClass + '" onclick=(setPage(\'' + index + '\'))>' + (index + 1) + '</button>&nbsp;&nbsp;'
    }

    // Ajout du bouton "Last"
    pagButton.innerHTML += '<button type="button" class="page-btn" onclick="setPage(' + (page_total - 1) + ')">Last</button>'
}


let setPage = (page) => {
    console.log('in set page : ', page)
    let current_page = parseInt(page)
    let pagButtons = document.getElementsByClassName('page-btn')
    for (let i = 0; i < pagButtons.length; i++) {
        pagButtons[i].classList.remove('page-btn-current-page')
    }
    let currentButton = document.querySelector('.page-btn-current-page')
    if (currentButton) {
        currentButton.classList.remove('page-btn-current-page')
    }
    let newButton = document.querySelector('[onclick="setPage(\'' + current_page + '\')"]')

    getProjectPerPage(current_page)
}

getProjectPerPage(0)
