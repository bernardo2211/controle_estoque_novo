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
    var linhas = document.querySelectorAll('.fonte-pequena');

    linhas.forEach(function(linha) {
        var nomeProduto = linha.parentElement.querySelector('td:nth-child(2)').textContent.toLowerCase();
        if (nomeProduto.includes(termo.toLowerCase())) {
            linha.parentElement.style.display = 'table-row';
        } else {
            linha.parentElement.style.display = 'none';
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
        if (event.target == sidebarprod) {
            sidebarprod.style.width = '0';
            selectedItemsDiv.innerHTML = '';
        }
    });

    document.querySelectorAll('.selectBtn').forEach(button => {
        button.addEventListener('click', () => {
            const itemId = button.getAttribute('data-item-id');
            const itemName = button.getAttribute('data-item-name');
            const existingItem = document.getElementById(`item-${itemId}`);
            if (!existingItem) {
                const itemDiv = document.createElement('div');
                itemDiv.id = `item-${itemId}`;
                itemDiv.innerHTML = `
                    <span>${itemName}</span>
                    <input type="hidden" name="items[${itemId}].itemId" value="${itemId}">
                    <input type="number" name="items[${itemId}].quantity" placeholder="Quantidade" data-item-id="${itemId}">
                `;
                selectedItemsDiv.appendChild(itemDiv);
            }
        });
    });
});

