<template>
  <header class="n-global-header n-global-header:guest">
    <NuxtLink href="/" class="n-heading:2">
      <img src="/assets/image/DevLinks.svg" alt="">
    </NuxtLink>

    <nav class="header-menu-container ml:auto">
      <h1 class="d:none">헤더메뉴</h1>

      <div class="d:flex align-items:center">
        <NuxtLink class="n-btn n-btn:hover" href="#">호스트 되기</NuxtLink>
      </div>

      <!--          v-if="!userDetails.isAnonymous()" -->
      <ul
          class="header-menus:icon-only md:show">
        <li class="header-menu">
          <NuxtLink
              class="n-btn n-btn:hover n-btn-bd:transparent n-icon n-icon:dashboard"
              href="#" :to="`/programs`"
          >프로그램 목록
          </NuxtLink
          >
        </li>
        <li class="header-menu">
          <NuxtLink
              class="n-btn n-btn:hover n-btn-bd:transparent n-icon n-icon:ticket"
              href="#"
          >내 예약
          </NuxtLink
          >
        </li>
        <li class="header-menu">
          <a
              class="n-btn n-btn:hover n-btn-bd:transparent n-icon n-icon:wishlist"
              href="#"
          >찜 목록
          </a
          >
        </li>
        <li class="header-menu">
          <NuxtLink
              class="n-btn n-btn:hover n-btn-bd:transparent n-icon n-icon:chat"
              href="#"
          >Chat
          </NuxtLink
          >
        </li>
      </ul>

      <!--          v-if="userDetails.isAnonymous()" -->
      <div
          class="profile-img-container md:show">
        <NuxtLink href="#" class="profile-img-wrapper">
          <img
              class="profile-img"
              src="#"
              alt="게스트 프로필 사진"
          />
        </NuxtLink>
      </div>

      <!--      v-if="!userDetails.isAnonymous()"-->
      <div class="profile-img-container md:show">
        <!--            @click.prevent="toggleModal" -->
        <div
            style="cursor: pointer" class="profile-img-wrapper">
          <!--              v-if="userDetails.profileImgSrc.value"-->
          <!--               :src="`http://localhost:8080/api/v1/${userDetails.profileImgSrc.value}`"-->
          <img
              class="profile-img"
              src="#"
              alt="게스트 프로필 사진"
          />
          <!--              v-else-->
          <img
              class="profile-img"
              src="#"
              alt="게스트 프로필 사진"
          />
        </div>
        <!--            v-if="showModal" -->
        <div
            class="modal-content">
          <!-- 닫기 버튼 -->
          <!--                  @click="showModal = false"-->
          <button class="modal-close"
          >×</button>

          <!-- 프로필 사진과 마이페이지 링크 -->
          <div class="modal-header">
            <!--                v-if="userDetails.profileImgSrc.value" -->
            <!--                 :src="`http://localhost:8080/api/v1/${userDetails.profileImgSrc.value}`" -->
            <img
                class="modal-profile-img"
                alt="프로필 사진"/>
            <!--                v-else -->
            <img
                class="modal-profile-img" src="#" alt="프로필 사진"/>
            <NuxtLink href="#" class="mypage-link">마이페이지</NuxtLink>
          </div>

          <!-- 로그아웃 버튼 -->
          <div class="modal-actions">
            <!--                @click.prevent="logoutHandler" -->
            <button
                class="logout-button">로그아웃</button>
          </div>
        </div>
      </div>
    </nav>

    <!-- 어사이드 메뉴 -->
    <section class="menu-icon md:d:none">
      <h1 class="d:none">어사이드 메뉴</h1>

      <!--햄버거 네비게이터-->
      <label
          for="menu-toggle"
          class="n-icon n-icon:list n-icon-size:6 margin-left:3 cursor:pointer"
      ></label>
      <input id="menu-toggle" class="n-aside-hider" type="checkbox"/>

      <!--          @click="closeAsideHandler" -->
      <div
          class="bg-darkened"></div>

      <!--어사이드-->
      <aside class="n-aside" style="height: 100vh; overflow-y: auto;">
        <header class="aside-header" style="overflow: visible; position: relative">
          <h1>게스트 로그인 어사이드</h1>
          <label
              for="menu-toggle"
              class="n-icon n-deco n-icon:exit cursor:pointer"
          ></label>
          <!--              v-if="!userDetails.isAnonymous()"-->
          <div
          >
            <span class="n-icon n-icon:chat"></span>
            <!--                  @click.prevent="toggleNotificationModal"-->
            <span v-if="(notifications && notifications.length > 0) || hasNewNotification"
                  class="n-icon n-icon:notification" style="cursor: pointer"></span>
            <!--                  @click.prevent="toggleNotificationModal"-->
            <span v-else
                  class="n-icon n-icon:alert" style="cursor: pointer"></span>
          </div>

          <!--              v-if="showNotificationModal" -->
          <div
              class="modal-content-notification-aside">
            <header>
              <!--                      @click="showNotificationModal = false"-->
              <button class="modal-close"
              >×</button>
              <div class="modal-header">
                <h2>알림</h2>
              </div>
            </header>
            <div class="modal-body d:flex fl-dir:column">
              <div style="margin-bottom: 10px">
                <!--                미확인 알림: {{ notificationCount }}개-->
              </div>
              <ul>
                <li
                    v-for="notification in notifications"
                    :key="notification.id"
                    class="notification-items"
                    :class="{
                    'bg-color-green': notification.type === 'RESERVE',
                    'bg-color-red': notification.type === 'CANCEL' || notification.type === 'CANCEL FROM HOST'
                     }"
                >
                  <div class="d:block">
                    <!--                        :href="notification.type === 'CANCEL FROM HOST'-->
                    <!--                      ? `/guest/reservations/${notification.reservationId}`-->
                    <!--                      : `/host/reservations/${notification.programId}`"-->
                    <NuxtLink
                    >
                      <span style="font-weight: bold">
<!--                        {{ notification.title }}-->
                      </span>
                    </NuxtLink>
                    <!--                    이(가) {{ notification.type === 'RESERVE' ? '예약' : notification.type === 'CANCEL' ? '취소' : '취소' }}-->
                    <!--                    되었습니다-->
                    <!--                    <div>{{ notification.regDate }}</div>-->
                  </div>
                  <!--                      @click.prevent="confirmNotification(notification.notificationId)"-->
                  <button
                      class="notification-close"
                  >
                    ×
                  </button>
                </li>
              </ul>
            </div>
          </div>

        </header>
        <!--            v-if="!userDetails.isAnonymous()" -->
        <section
            class="aside-profile">
          <h1>게스트 프로필</h1>
          <div class="profile-img-container">
            <!--                @click.prevent="toggleModal" -->
            <div
                class="profile-img-wrapper" style="cursor: pointer">
              <!--                  v-if="userDetails.profileImgSrc.value"-->
              <!--                   :src="`http://localhost:8080/api/v1/${userDetails.profileImgSrc.value}`"-->
              <img
                  class="profile-img"
                  alt="게스트 프로필 사진"
              />
              <!--                  v-else-->
              <img
                  class="profile-img"
                  src=""
                  alt="게스트 프로필 사진"
              />
            </div>
          </div>
          <div class="profile-info">
            <p class="nickname">
              <!--              {{ data.nickname }}-->
            </p>
            <div class="account-setting">
              <NuxtLink href="#" class="n-icon n-deco n-icon:setting">내 계정관리</NuxtLink>
            </div>
          </div>
        </section>
        <!--            v-else -->
        <section
            class="aside-logout">
          <h1 class="aside-main-title">Haru Seoul</h1>
          <NuxtLink href="#" class="n-btn n-btn-background-color:main">로그인 및 회원가입</NuxtLink>
        </section>
        <nav>
          <h1 class="d:none">Guest Menu</h1>
          <!--              v-if="!userDetails.isAnonymous()" -->
          <details
              open class="filter">
            <summary class="collapse">
              <span class="title">Guest Menu</span>
              <span class="n-icon n-icon:arrow_down">펼치기 버튼</span>
            </summary>
            <ul class="aside-menu">
              <li>
                <NuxtLink href="/guest/reservations" class="menu n-icon n-deco n-icon:ticket">내 예약</NuxtLink>
              </li>
              <li>
                <a href="#" class="menu n-icon n-deco n-icon:wishlist">찜 목록</a>
              </li>
              <!--            <li><a href="#" class="menu n-icon n-deco n-icon:chat">Chat</a></li>-->
            </ul>
          </details>
          <!--              v-if="!userDetails.isAnonymous()" -->
          <details
              open class="filter">
            <summary class="collapse">
              <span class="title">Host Menu</span>
              <span class="n-icon n-icon:arrow_down">펼치기 버튼</span>
            </summary>
            <ul class="aside-menu">
              <li>
                <a href="" class="menu n-icon n-deco n-icon:dashboard"
                >대시보드</a
                >
              </li>
              <li>
                <NuxtLink href="/host/programs/new" class="menu n-icon n-deco n-icon:create"
                >프로그램 등록
                </NuxtLink
                >
              </li>
              <li>
                <NuxtLink href="/host/programs" class="menu n-icon n-deco n-icon:programs"
                >프로그램 관리
                </NuxtLink
                >
              </li>
              <li>
                <NuxtLink href="/host/reservations" class="menu n-icon n-deco n-icon:check_doc"
                >예약 관리
                </NuxtLink
                >
              </li>
              <li>
                <a href="#" class="menu n-icon n-deco n-icon:review"
                >리뷰 모아보기</a
                >
              </li>
              <!--              <li><a href="#" class="menu n-icon n-deco n-icon:chat">Chat</a></li>-->
            </ul>
          </details>
          <details open class="filter">
            <summary class="collapse">
              <span class="title">Customer Center</span>
              <span class="n-icon n-icon:arrow_down">펼치기 버튼</span>
            </summary>
            <ul class="aside-menu">
              <li>
                <a href="#" class="menu n-icon n-deco n-icon:notice">공지사항</a>
              </li>
              <li>
                <a href="#" class="menu n-icon n-deco n-icon:faq"
                >자주 묻는 질문</a
                >
              </li>
            </ul>
          </details>
          <ul class="aside-menu bd-color:transparent">
            <li>
              <!--              v-if="!userDetails.isAnonymous()" @click.prevent="logoutHandler"-->
              <div
                  style="cursor: pointer"
                  class="menu n-icon n-deco n-icon:logout">로그아웃
              </div>
            </li>
          </ul>
        </nav>
        <div
            class="aside-footer n-icon n-deco n-icon:globe_2 n-icon-color:main-3"
        >
          <select name="language" id="language">
            <option value="Korean">Korean</option>
            <option value="English">English</option>
            <option value="Chinese">Chinese</option>
            <option value="Japanese">Japanese</option>
          </select>
        </div>
      </aside>
    </section>
  </header>
</template>
<script setup lang="ts">
</script>