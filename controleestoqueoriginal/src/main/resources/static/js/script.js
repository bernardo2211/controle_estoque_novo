document.addEventListener('DOMContentLoaded', () => {
    const sidebarprod = document.getElementById('sidebarprod');
    const openSidebarBtn = document.getElementById('openSidebarBtn');
    const closeBtn = document.querySelector('.close');
    const selectedItemsDiv = document.getElementById('selectedItems');

    openSidebarBtn.addEventListener('click', () => {
        sidebarprod.style.width = '1200px';
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
