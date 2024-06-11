document.addEventListener("DOMContentLoaded", function () {
    const darkModeToggle = document.getElementById("dark-mode-toggle");

    // Verifique se o elemento existe para evitar erros
    if (darkModeToggle) {
        darkModeToggle.addEventListener("click", function () {
            document.body.classList.toggle("dark-theme");
            const darkModeEnabled = document.body.classList.contains("dark-theme");
            localStorage.setItem("darkModeEnabled", darkModeEnabled.toString());
            //salva a configuração do dark mode de forma local, caso seja trocado de página
        });

        // Verifica se está com dark mode ativado, mantendo esta configuração se for verdadeira
        const darkModeEnabled = localStorage.getItem("darkModeEnabled");

        if (darkModeEnabled === "true") {
            document.body.classList.add("dark-theme");
        }
    }
});
