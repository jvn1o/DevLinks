<template>
  <img
      class="logo-responsive img-non-filter"
      src="/assets/image/DevLinks.svg"
      alt="Logo"
      width="30"
      height="30"
  />
  <span class="text-gradient logo-text">
    DevLinks
  </span>
</template>

<style scoped>
.text-gradient {
  background: linear-gradient(to right, #007cf0, #00dfd8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.logo-text {
  font-weight: bold;
  font-size: 2.3rem;
}

@media (max-width: 768px) {
  .logo-responsive {
    width: 20px;
    height: 20px;
  }

  .logo-text {
    font-size: 1.4rem;
  }
}
</style>