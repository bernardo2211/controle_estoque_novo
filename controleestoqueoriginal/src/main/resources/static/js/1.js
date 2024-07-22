var menuItem = document.querySelectorAll('.item-menu')

function selectLink(){
    menuItem.forEach((item)=>
        item.classList.remove('ativo')
    )
    this.classList.add('ativo')
}

menuItem.forEach((item)=>
    item.addEventListener('click', selectLink)
)

var btnExp = document.querySelector('#btn-exp')
var menuSide = document.querySelector('.menu-lateral')

btnExp.addEventListener('click', function(){
    menuSide.classList.toggle('expandir')
})


function buscarProduto(termo) {
    var linhas = document.querySelectorAll('tbody tr');

    linhas.forEach(function(linha) {
        var nomeProduto = linha.querySelector('td:nth-child(2)').textContent.toLowerCase();
        if (nomeProduto.includes(termo.toLowerCase())) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    });
}


document.addEventListener('DOMContentLoaded', () => {
    const sidebarprod = document.getElementById('sidebarprod');
    const openSidebarBtn = document.getElementById('openSidebarBtn');
    const closeBtn = document.querySelector('.close');
    const selectedItemsDiv = document.getElementById('selectedItems');

    openSidebarBtn.addEventListener('click', () => {
        sidebarprod.style.width = '1265px';
    });

    closeBtn.addEventListener('click', () => {
        sidebarprod.style.width = '0';
        selectedItemsDiv.innerHTML = '';
    });

    window.addEventListener('click', (event) => {
        if (event.target === sidebarprod) {
            sidebarprod.style.width = '0';
            selectedItemsDiv.innerHTML = '';
        }
    });

    function openReportInNewTab(url) {
        var win = window.open(url, '_blank');
        win.focus();
    }

    function handleFormSubmit(event) {
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);
        const queryString = new URLSearchParams(formData).toString();
        
        fetch(form.action, {
            method: 'POST',
            body: formData
        }).then(response => {
            if (response.redirected) {
                openReportInNewTab(response.url);
            }
        }).catch(error => console.error('Error:', error));
    }

})

