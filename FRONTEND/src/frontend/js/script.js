    const API_URL = "http://localhost:8080/api/products";

    const header = document.querySelector('.site-header');
    const searchToggleBtn = document.getElementById('searchToggleBtn');
    const searchInput = document.getElementById('searchInput');

    searchToggleBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        header.classList.toggle('search-active');
        
        if (header.classList.contains('search-active')) {
            setTimeout(() => { searchInput.focus(); }, 400);
        }
        });

        document.addEventListener('click', (e) => {
        const searchContainer = document.getElementById('searchContainer');
        if (!searchContainer.contains(e.target) && e.target !== searchToggleBtn) {
            header.classList.remove('search-active');
        }
        });
        // Дожидаемся, пока вся страница полностью загрузится
    document.addEventListener('DOMContentLoaded', () => {
    
    // 1. Находим нужные элементы по их ID
    const loginBtn = document.getElementById('loginBtn');
    const loginModal = document.getElementById('loginModal');
    const modalCloseBtn = document.getElementById('modalCloseBtn');

    // Проверка на случай, если кнопка или окно не найдены (чтобы код не ломался)
    if (!loginBtn || !loginModal) {
        console.error("Кнопка логина или модальное окно не найдены в HTML!");
        return;
    }

    // 2. Клик по иконке "Войти" — открывает окно
    loginBtn.addEventListener('click', (e) => {
        e.preventDefault(); // Отменяем стандартное поведение (если это ссылка-якорь)
        loginModal.classList.add('active'); // Добавляем класс, который делает окно видимым
    });

    // 3. Клик по крестику — закрывает окно
    if (modalCloseBtn) {
        modalCloseBtn.addEventListener('click', () => {
        loginModal.classList.remove('active');
        });
    }

    // 4. Клик по серому фону вокруг белого окошка — тоже закрывает его
    loginModal.addEventListener('click', (e) => {
        if (e.target === loginModal) {
        loginModal.classList.remove('active');
        }
    });

    });
    
    // --- ИДЕАЛЬНО ПЛАВНЫЙ СЛАЙДЕР С ПРАВОЙ СТРЕЛКОЙ И ТОЧКАМИ ---

let activeIndex = 0;
const totalSlides = 3; // Количество баннеров
const wrapper = document.querySelector('.carousel-slides-wrapper');
const miniDots = document.querySelectorAll('.mini-dot');

function updateSlider() {
  if (!wrapper) return;

  // Рассчитываем смещение в процентах (0%, -100%, -200%)
  const offset = activeIndex * -100;
  wrapper.style.transform = `translateX(${offset / totalSlides}%)`;

  // Обновляем внешний вид точек
  miniDots.forEach((dot, idx) => {
    if (idx === activeIndex) {
      dot.classList.add('active');
    } else {
      dot.classList.remove('active');
    }
  });
}

// Функция для клика по единственной правой стрелке
function nextSlide() {
  activeIndex++;
  if (activeIndex >= totalSlides) {
    activeIndex = 0; // Возвращаемся к первому, если дошли до конца
  }
  updateSlider();
}

// Функция для клика напрямую по точкам
function goToSlide(index) {
  activeIndex = index;
  updateSlider();
}

// Настраиваем автоматическое переключение раз в 5 секунд
if (wrapper) {
  setInterval(() => {
    nextSlide();
  }, 5000);
}

// --- ИНТЕРАКТИВ СТРАНИЦЫ ТОВАРА (РАЗМЕРЫ И ПРЕВЬЮ) ---

// 1. Переключение размеров
const sizeButtons = document.querySelectorAll('.size-btn:not(.disabled)');
sizeButtons.forEach(button => {
  button.addEventListener('click', () => {
    // Снимаем выделение со всех кнопок размеров
    sizeButtons.forEach(btn => btn.classList.remove('selected'));
    // Добавляем класс текущей кнопке
    button.classList.add('selected');
  });
});

// 2. Переключение маленьких превью фотографий
const thumbs = document.querySelectorAll('.thumb-stub');
const mainImageBox = document.querySelector('.main-image-box');

thumbs.forEach((thumb, index) => {
  thumb.addEventListener('click', () => {
    // Убираем активный класс у всех миниатюр
    thumbs.forEach(t => t.classList.remove('active'));
    // Делаем активной текущую
    thumb.classList.add('active');
    
    // Меняем текст внутри большой коробки для наглядности
    if (mainImageBox) {
      mainImageBox.textContent = `Большое фото товара ${index + 1}`;
    }
  });
});