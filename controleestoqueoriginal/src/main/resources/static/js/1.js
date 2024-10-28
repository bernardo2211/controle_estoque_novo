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
    const query = termo.toLowerCase(); // Transforma o termo em minúsculas
    const linhas = document.querySelectorAll('.fonte tbody tr'); // Seleciona todas as linhas da tabela

    linhas.forEach(function(linha) {
        const nomeProduto = linha.querySelector('td:nth-child(2)').textContent.toLowerCase(); // Obtém o texto da segunda célula (nome do produto)
        
        // Verifica se o nome do produto inclui o termo da busca
        if (nomeProduto.includes(query)) {
            linha.style.display = ''; // Mostra a linha se o nome incluir o termo
        } else {
            linha.style.display = 'none'; // Esconde a linha se não incluir
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

function ativarEdicao(button) {
    // Acha o <tr> pai do botão clicado
    var row = button.closest('tr');
    
    // Habilita todos os inputs da linha
    var inputs = row.querySelectorAll('input');
    inputs.forEach(function(input) {
        input.removeAttribute('readonly');
    });

    // Mostra o botão "Salvar"
    var saveButton = row.querySelector('button[type="submit"]');
    saveButton.style.display = 'inline-block';

    // Esconde o botão "Editar"
    button.style.display = 'none';
}





