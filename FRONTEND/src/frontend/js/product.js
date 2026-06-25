const PRODUCT_API = 'http://localhost:8080/api/products';
let selectedVariantId = null;
let currentProduct = null;

const urlParams = new URLSearchParams(window.location.search);
const productId = parseInt(urlParams.get('id'));

const LOCAL_PRODUCTS = [
  { id: 1, name: 'Платье повседневное',  brand: 'Mango',          price: 4999,  category: 'Платье',    sex: 'Женский',      description: 'Лёгкое повседневное платье. Хлопок 95%, Эластан 5%.',       sizes: ['40','42','44','46'],          soldOut: [] },
  { id: 2, name: 'Кроссовки Air Max',    brand: 'Nike',           price: 12499, category: 'Кроссовки', sex: 'Универсальный', description: 'Культовые кроссовки с амортизацией Air.',                   sizes: ['38','39','40','41','42','43'],soldOut: ['41'] },
  { id: 3, name: 'Куртка демисезонная',  brand: 'Zarina',         price: 6599,  category: 'Куртка',    sex: 'Женский',      description: 'Лёгкая куртка для переходного сезона.',                     sizes: ['42','44','46','48'],          soldOut: [] },
  { id: 4, name: 'Сумка через плечо',    brand: 'Guess',          price: 14100, category: 'Сумка',     sex: 'Женский',      description: 'Стильная сумка из эко-кожи.',                               sizes: ['one size'],                   soldOut: [] },
  { id: 5, name: 'Джинсы прямого кроя',  brand: 'Topshop',        price: 5200,  category: 'Джинсы',    sex: 'Женский',      description: 'Классические прямые джинсы.',                               sizes: ['XS','S','M','L','XL'],        soldOut: [] },
  { id: 6, name: 'Футболка хлопковая',   brand: 'Calvin Klein',   price: 3899,  category: 'Футболка',  sex: 'Универсальный', description: 'Базовая хлопковая футболка.',                              sizes: ['XS','S','M','L','XL'],        soldOut: [] },
  { id: 7, name: 'Свитшот оверсайз',     brand: 'Tommy Hilfiger', price: 11990, category: 'Свитшот',   sex: 'Универсальный', description: 'Оверсайз свитшот с фирменным логотипом.',                  sizes: ['S','M','L','XL'],             soldOut: ['S'] },
  { id: 8, name: 'Юбка плиссированная',  brand: 'Befree',         price: 2599,  category: 'Юбка',      sex: 'Женский',      description: 'Романтичная плиссированная юбка.',                          sizes: ['40','42','44','46','48'],     soldOut: [] },
  { id: 9, name: 'Кеды кожаные',         brand: 'Puma',           price: 8450,  category: 'Кеды',      sex: 'Универсальный', description: 'Классические кожаные кеды на плоской подошве.',            sizes: ['38','39','40','41','42'],     soldOut: [] },
];

function renderProduct(product, variants) {
  currentProduct = product;
  document.title = `${product.brand || product.name} | ABDULOVV STORE`;
  document.getElementById('productName').textContent = product.brand || product.name;
  document.getElementById('productCategory').textContent = product.name;
  document.getElementById('productPrice').textContent = Number(product.price).toLocaleString('ru-RU') + ' ₽';
  document.getElementById('productDescription').textContent = product.description || '';
  document.getElementById('productSex').textContent = product.sex || '—';

  const sizesEl = document.getElementById('sizes');
  sizesEl.innerHTML = '';
  variants.forEach(v => {
    const btn = document.createElement('button');
    const isOut = v.stock !== undefined ? v.stock <= 0 : false;
    btn.className = 'size-btn' + (isOut ? ' disabled' : '');
    btn.textContent = v.size;
    btn.disabled = isOut;
    if (!isOut) {
      btn.addEventListener('click', () => {
        document.querySelectorAll('.size-btn').forEach(b => b.classList.remove('selected'));
        btn.classList.add('selected');
        selectedVariantId = v.id ?? v.size;
      });
    }
    sizesEl.appendChild(btn);
  });
}

async function loadProduct() {
  if (!productId) { showFallback(); return; }
  try {
    const [productRes, variantsRes] = await Promise.all([
      fetch(`${PRODUCT_API}/${productId}`),
      fetch(`${PRODUCT_API}/${productId}/variants`)
    ]);
    if (!productRes.ok) throw new Error('not ok');
    const product = await productRes.json();
    const variants = await variantsRes.json();
    renderProduct(product, variants);
  } catch {
    showFallback();
  }
}

function showFallback() {
  const p = LOCAL_PRODUCTS.find(x => x.id === productId);
  if (!p) {
    document.getElementById('productName').textContent = 'Товар не найден';
    return;
  }
  const variants = p.sizes.map((s, i) => ({
    id: i,
    size: s,
    stock: p.soldOut.includes(s) ? 0 : 10
  }));
  renderProduct(p, variants);
}

async function addToCart() {
  if (selectedVariantId === null) {
    alert('Выберите размер'); return;
  }
  try {
    const res = await fetch('http://localhost:8080/api/cart', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ user: { id: 1 }, productVariant: { id: selectedVariantId }, quantity: 1 })
    });
    if (res.ok) { alert('Товар добавлен в корзину'); return; }
  } catch {}
  const cart = JSON.parse(localStorage.getItem('cart') || '[]');
  const btn = document.querySelector('.size-btn.selected');
  cart.push({ id: currentProduct.id, brand: currentProduct.brand, name: currentProduct.name, price: currentProduct.price, size: btn?.textContent });
  localStorage.setItem('cart', JSON.stringify(cart));
  alert('Добавлено в корзину!');
}

function addToFav() {
  if (!currentProduct) return;
  const favs = JSON.parse(localStorage.getItem('favorites') || '[]');
  if (!favs.find(f => f.id === currentProduct.id)) {
    favs.push({ id: currentProduct.id, brand: currentProduct.brand, name: currentProduct.name, price: currentProduct.price });
    localStorage.setItem('favorites', JSON.stringify(favs));
  }
  const btn = document.querySelector('.add-to-fav-btn');
  btn.textContent = '♥';
  btn.style.color = '#e74c3c';
}

loadProduct();