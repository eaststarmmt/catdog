<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4" v-if="!modifying">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <b-container class="mt-4" v-if="modifying">
            <b-form class="text-left">
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  ref="userid"
                  v-model="user.userid"
                  required
                  readonly
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름:" label-for="username">
                <b-form-input
                  id="username"
                  ref="username"
                  v-model="user.username"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  id="email"
                  ref="email"
                  v-model="user.email"
                  required
                ></b-form-input>
              </b-form-group>
            </b-form>
          </b-container>
          <hr class="my-4" />

          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            v-if="!modifying"
            @click="modifyInfo"
            >정보수정</b-button
          >
          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            v-if="modifying"
            @click="confirmModify"
            >수정</b-button
          >
          <b-button
            variant="danger"
            href="#"
            v-if="!modifying"
            @click="removeInfo"
            >회원탈퇴</b-button
          >
          <b-button
            variant="danger"
            href="#"
            v-if="modifying"
            @click="cancelModify"
            >취소</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";
import { modifyUserById, deleteUserById } from "../../api/member.js";

export default {
  name: "MemberMyPage",
  components: {},
  data() {
    return {
      modifying: false,
      user: {
        userid: null,
        username: null,
        userpwd: null,
        email: null,
      },
    };
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.email = this.userInfo.email;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    modifyInfo() {
      console.log(this.userInfo);

      console.log(this.user);
      this.modifying = true;
    },
    cancelModify() {
      this.modifying = false;
    },
    confirmModify() {
      //수정한 내용대로 db수정하고
      modifyUserById(this.user);
      this.getModifiedInfo();
    },
    async getModifiedInfo() {
      // getuserinfo로 불러와서 userinfo update함
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);
      //그리고 다시 mypage로감
      alert("수정완료!");
      this.modifying = false;
    },
    removeInfo() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        //삭제하고 로그아웃한 후 홈으로
        deleteUserById(this.user.userid);
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");
        alert("탈퇴완료!");
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>

<style></style>
