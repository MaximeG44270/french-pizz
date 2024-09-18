const slides = document.querySelectorAll(".slides img");
let slideIndex = 0;
let intervalId = null;

document.addEventListener("DOMContentLoaded", initializeSlider);

function initializeSlider() {
    if (slides.length > 0) {
        slides[slideIndex].classList.add("displaySlide");
        intervalId = setInterval(nextSlide, 5000); // Réduisez l'intervalle pour des tests plus rapides
        updateButtons();
    }
}

function showSlide(index) {
    if (index >= slides.length) {
        slideIndex = slides.length - 1;
    } else if (index < 0) {
        slideIndex = 0;
    } else {
        slideIndex = index;
    }
    slides.forEach(slide => {
        slide.classList.remove("displaySlide");
    });
    slides[slideIndex].classList.add("displaySlide");
    updateButtons();
}

function prevSlide() {
    clearInterval(intervalId);
    slideIndex--;
    showSlide(slideIndex);
}

function nextSlide() {
    slideIndex++;
    showSlide(slideIndex);
}

function updateButtons() {
    const prevButton = document.getElementById("prevButton");
    const nextButton = document.getElementById("nextButton");

    if (slideIndex === 0) {
        prevButton.style.display = "none";
    } else {
        prevButton.style.display = "inline-block";
    }

    if (slideIndex === slides.length - 1) {
        nextButton.style.display = "none";
    } else {
        nextButton.style.display = "inline-block";
    }
}