const state = {
  token: localStorage.getItem("ecommerce.token") || "",
  email: localStorage.getItem("ecommerce.email") || "",
};

const dom = {
  sessionStatus: document.querySelector("#sessionStatus"),
  logoutButton: document.querySelector("#logoutButton"),
  validateTokenButton: document.querySelector("#validateTokenButton"),
  productGrid: document.querySelector("#productGrid"),
  searchForm: document.querySelector("#searchForm"),
  loginForm: document.querySelector("#loginForm"),
  registerForm: document.querySelector("#registerForm"),
  productForm: document.querySelector("#productForm"),
  deleteProductButton: document.querySelector("#deleteProductButton"),
  loadCartButton: document.querySelector("#loadCartButton"),
  cartItems: document.querySelector("#cartItems"),
  cartUserId: document.querySelector("#cartUserId"),
  productEmail: document.querySelector("#productEmail"),
  toast: document.querySelector("#toast"),
};

const api = {
  async request(path, options = {}) {
    const headers = {
      Accept: "application/json",
      ...(options.body ? { "Content-Type": "application/json" } : {}),
      ...(state.token ? { Authorization: `Bearer ${state.token}` } : {}),
      ...options.headers,
    };

    const response = await fetch(path, { ...options, headers });
    const text = await response.text();
    const data = parseResponse(text);

    if (!response.ok) {
      throw new Error(getMessage(data) || `Erro ${response.status} em ${path}`);
    }

    return data;
  },
};

function parseResponse(text) {
  if (!text) return {};
  try {
    return JSON.parse(text);
  } catch {
    return { message: text };
  }
}

function getMessage(data) {
  if (!data) return "";
  return data.Response || data.response || data.message || data.Message || data.Status || "";
}

function showToast(message) {
  dom.toast.textContent = message;
  dom.toast.classList.add("visible");
  clearTimeout(showToast.timer);
  showToast.timer = setTimeout(() => dom.toast.classList.remove("visible"), 3600);
}

function saveSession(token, email) {
  state.token = token || "";
  state.email = email || "";
  if (state.token) {
    localStorage.setItem("ecommerce.token", state.token);
    localStorage.setItem("ecommerce.email", state.email);
  } else {
    localStorage.removeItem("ecommerce.token");
    localStorage.removeItem("ecommerce.email");
  }
  syncSessionUi();
}

function syncSessionUi() {
  dom.sessionStatus.textContent = state.token ? `Conectado: ${state.email}` : "Visitante";
  dom.cartUserId.value = dom.cartUserId.value || state.email;
  dom.productEmail.value = dom.productEmail.value || state.email;
}

function money(value) {
  return Number(value || 0).toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
}

function initials(name) {
  return String(name || "P").trim().split(/\s+/).slice(0, 2).map((part) => part[0]).join("").toUpperCase();
}

function escapeHtml(value) {
  return String(value ?? "")
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#039;");
}

async function loadProducts(event) {
  event?.preventDefault();
  const params = new URLSearchParams();
  const nome = document.querySelector("#searchName").value.trim();
  const minPreco = document.querySelector("#minPrice").value;
  const maxPreco = document.querySelector("#maxPrice").value;

  if (nome) params.set("nome", nome);
  if (minPreco) params.set("minPreco", minPreco);
  if (maxPreco) params.set("maxPreco", maxPreco);
  params.set("size", "24");

  dom.productGrid.innerHTML = `<div class="empty-state">Carregando produtos...</div>`;

  try {
    const data = await api.request(`/api/v1/search?${params.toString()}`);
    renderProducts(data.produtos || []);
  } catch (error) {
    dom.productGrid.innerHTML = `<div class="empty-state">Não foi possível carregar os produtos.</div>`;
    showToast(error.message);
  }
}

function renderProducts(products) {
  if (!products.length) {
    dom.productGrid.innerHTML = `<div class="empty-state">Nenhum produto encontrado. Crie um item no painel lateral.</div>`;
    return;
  }

  dom.productGrid.innerHTML = products.map((product) => `
    <article class="product-card">
      <div class="product-visual">${initials(product.nome)}</div>
      <div>
        <h3 class="product-title">${escapeHtml(product.nome)}</h3>
        <p class="product-description">${escapeHtml(product.descricao || "Sem descrição")}</p>
      </div>
      <div class="product-meta">
        <span class="price">${money(product.preco)}</span>
        <span class="stock">${Number(product.estoque || 0)} em estoque</span>
      </div>
      <form class="add-row" data-product-id="${product.id}">
        <input type="number" min="1" step="1" value="1" aria-label="Quantidade">
        <button class="primary-button" type="submit">Adicionar</button>
      </form>
    </article>
  `).join("");

  document.querySelectorAll(".add-row").forEach((form) => form.addEventListener("submit", addToCart));
}

async function login(event) {
  event.preventDefault();
  const email = document.querySelector("#loginEmail").value.trim();
  const password = document.querySelector("#loginPassword").value;

  try {
    const data = await api.request("/api/v1/auth/login", {
      method: "POST",
      body: JSON.stringify({ email, password }),
    });
    saveSession(data.token, email);
    showToast("Login realizado com sucesso.");
  } catch (error) {
    showToast(error.message);
  }
}

async function register(event) {
  event.preventDefault();
  const nome = document.querySelector("#registerName").value.trim();
  const email = document.querySelector("#registerEmail").value.trim();
  const password = document.querySelector("#registerPassword").value;

  try {
    const data = await api.request("/api/v1/users/newUsers", {
      method: "POST",
      body: JSON.stringify({ nome, email, password }),
    });
    showToast(getMessage(data) || "Conta criada.");
    document.querySelector("#loginEmail").value = email;
    document.querySelector("#loginPassword").value = password;
    setActiveTab("login");
  } catch (error) {
    showToast(error.message);
  }
}

async function validateToken() {
  try {
    const data = await api.request("/api/v1/auth/token");
    showToast(getMessage(data) || "Token válido.");
  } catch (error) {
    showToast(error.message);
  }
}

async function saveProduct(event) {
  event.preventDefault();
  const action = event.submitter?.dataset.action || "create";
  const payload = {
    nome: document.querySelector("#productName").value.trim(),
    preco: Number(document.querySelector("#productPrice").value),
    estoque: Number(document.querySelector("#productStock").value),
    descricao: document.querySelector("#productDescription").value.trim(),
    email: document.querySelector("#productEmail").value.trim(),
  };

  try {
    const data = await api.request(`/api/v1/product/${action}`, {
      method: "POST",
      body: JSON.stringify(payload),
    });
    showToast(getMessage(data) || "Produto salvo.");
    await loadProducts();
  } catch (error) {
    showToast(error.message);
  }
}

async function deleteProduct() {
  const id = document.querySelector("#deleteProductId").value;
  if (!id) {
    showToast("Informe o ID do produto para excluir.");
    return;
  }

  try {
    const data = await api.request(`/api/v1/product/delete/${id}`, { method: "DELETE" });
    showToast(getMessage(data) || "Produto excluído.");
    await loadProducts();
  } catch (error) {
    showToast(error.message);
  }
}

async function addToCart(event) {
  event.preventDefault();
  const form = event.currentTarget;
  const userId = dom.cartUserId.value.trim() || state.email;
  const produtoId = form.dataset.productId;
  const quantidade = Number(form.querySelector("input").value || 1);

  if (!userId) {
    showToast("Informe o usuário do carrinho ou faça login.");
    return;
  }

  try {
    await api.request("/api/v1/cartShopping/add", {
      method: "POST",
      body: JSON.stringify({ userId, produtoId, quantidade }),
    });
    showToast("Item adicionado ao carrinho.");
    await loadCart();
  } catch (error) {
    showToast(error.message);
  }
}

async function loadCart() {
  const userId = dom.cartUserId.value.trim() || state.email;
  if (!userId) {
    showToast("Informe o usuário do carrinho.");
    return;
  }

  dom.cartItems.innerHTML = `<div class="empty-state">Carregando carrinho...</div>`;

  try {
    const data = await api.request(`/api/v1/cartShopping/${encodeURIComponent(userId)}`);
    renderCart(data);
  } catch (error) {
    dom.cartItems.innerHTML = "";
    showToast(error.message);
  }
}

function renderCart(cart) {
  const items = cart.itens || [];
  if (!items.length) {
    dom.cartItems.innerHTML = `<div class="empty-state">Carrinho vazio.</div>`;
    return;
  }

  dom.cartItems.innerHTML = items.map((item) => `
    <div class="cart-item">
      <strong>Produto ${escapeHtml(item.produtoId)}</strong>
      <span>${Number(item.quantidade || 0)} un.</span>
    </div>
  `).join("");
}

function setActiveTab(name) {
  document.querySelectorAll(".tab-button").forEach((button) => {
    button.classList.toggle("active", button.dataset.tab === name);
  });
  document.querySelectorAll(".tab-content").forEach((content) => {
    content.classList.toggle("active", content.id === `${name}Form`);
  });
}

document.querySelectorAll(".tab-button").forEach((button) => {
  button.addEventListener("click", () => setActiveTab(button.dataset.tab));
});

dom.searchForm.addEventListener("submit", loadProducts);
dom.loginForm.addEventListener("submit", login);
dom.registerForm.addEventListener("submit", register);
dom.productForm.addEventListener("submit", saveProduct);
dom.deleteProductButton.addEventListener("click", deleteProduct);
dom.loadCartButton.addEventListener("click", loadCart);
dom.validateTokenButton.addEventListener("click", validateToken);
dom.logoutButton.addEventListener("click", () => {
  saveSession("", "");
  showToast("Sessão encerrada.");
});

syncSessionUi();
loadProducts();
