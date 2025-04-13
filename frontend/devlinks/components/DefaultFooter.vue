<template>
  <footer class="n-footer bg-light py-5 mt-5">
    <div class="container">
      <div class="row">

        <!-- Product Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Product</h5>
          <ul class="list-unstyled">
            <li><NuxtLink to="/features" class="text-decoration-none">Features</NuxtLink></li>
            <li><NuxtLink to="/docs" class="text-decoration-none">Documentation</NuxtLink></li>
            <li><NuxtLink to="/blog" class="text-decoration-none">Blog</NuxtLink></li>
          </ul>
        </div>

        <!-- Community Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Community</h5>
          <ul class="list-unstyled">
            <li>
              <a href="https://github.com/jvn1o" target="_blank" class="text-decoration-none">
                GitHub
              </a>
            </li>
            <li>
              <a href="https://discord.gg/yourcommunity" target="_blank" class="text-decoration-none">
                Discord
              </a>
            </li>
            <li>
              <a href="https://www.notion.so/9abf00bcde874b929db4315dda03238f?pvs=4" target="_blank" class="text-decoration-none">
                Notion
              </a>
            </li>
          </ul>
        </div>

        <!-- Legal Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Legal</h5>
          <ul class="list-unstyled">
            <li><NuxtLink to="/terms" class="text-decoration-none">Terms</NuxtLink></li>
            <li><NuxtLink to="/privacy" class="text-decoration-none">Privacy</NuxtLink></li>
          </ul>
        </div>

        <!-- Newsletter + Language -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Stay in the loop</h5>
          <form @submit.prevent class="d-flex flex-column">
            <input type="email" placeholder="you@example.com" class="form-control mb-2" />
            <button class="btn btn-primary">Subscribe</button>
          </form>

          <div class="mt-4">
            <label for="lang" class="form-label">Language</label>
            <select id="lang" class="form-select">
              <option>Korean</option>
              <option>English</option>
            </select>
          </div>
        </div>

      </div>

      <!-- Bottom Section -->
      <div class="text-center mt-5">
        <div class="d-flex justify-content-center gap-3 mb-3">
          <a href="https://github.com/jvn1o" target="_blank">
            <img src="/assets/image/icon/github.svg" alt="GitHub" width="24" />
          </a>
          <a href="https://discord.gg/yourcommunity" target="_blank">
            <img src="/assets/image/icon/discord.svg" alt="Discord" width="24" />
          </a>
        </div>

        <small class="text-muted">
          © {{ new Date().getFullYear() }} DevLinks. Built with ❤️ by KJS.
        </small>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.n-footer a {
  color: #007bff;
}
.n-footer a:hover {
  color: #0056b3;
  text-decoration: underline;
}

select.form-select {
  background-color: #fff;
  border: 1px solid #ced4da;
  font-size: 0.9rem;
}
</style>
