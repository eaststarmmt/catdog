<template>
  <div>
    <h2>댓글 작성</h2>
    <b-form @submit="onSubmit">
      <b-form-group
        id="userid-group"
        label="작성자:"
        label-for="username"
        description="작성자를 입력하세요."
      >
        <b-form-input
          id="username"
          v-model="userInfo.username"
          type="hidden"
          required
          placeholder="작성자 입력..."
        ></b-form-input>
      </b-form-group>

      <b-form-group id="content-group" label="내용:" label-for="content">
        <b-form-textarea
          id="content"
          placeholder="내용 입력..."
          rows="10"
          max-rows="15"
          v-model="reply.content"
        ></b-form-textarea>
        <input
          type="hidden"
          name="userid"
          id="userid"
          v-model="userInfo.userid"
        />
        <input type="hidden" name="boardid" id="boardid" :value="articleno" />
      </b-form-group>

      <b-button type="submit" variant="primary" class="m-1">글작성</b-button>
      <b-button type="submit" variant="primary" class="m-1">글수정</b-button>
      <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { writeReply } from "@/api/reply";

const memberStore = "memberStore";
export default {
  name: "ReplyWrite",
  data() {
    return {
      type: "register",
    };
  },
  props: {
    articleno: Number,
  },
  create() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      // let err = true;
      // let msg = "";
      // this.registReply();
    },
    registReply() {
      writeReply(
        {
          userid: this.userid,
          username: this.username,
          content: this.content,
          boardid: this.boardid,
        },
        ({ data }) => {
          let msg = "댓글 등록 실패!!!!";
          if (data === "success") {
            msg = "댓글 등록 완료!!!";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
