<template>
  <b-tr>
    <b-td>{{ username }}</b-td>
    <b-td v-if="con">{{ replyContent }}</b-td>
    <b-td
      ><b-form-textarea
        class="replyContent"
        placeholder="내용 입력..."
        rows="10"
        max-rows="15"
        v-model="content"
        v-if="update"
      ></b-form-textarea
    ></b-td>
    <b-td
      ><b-button
        type="button"
        variant="primary"
        class="m-1"
        @click="updateForm()"
        v-if="con"
        >수정</b-button
      >
      <b-button
        type="button"
        variant="primary"
        class="m-1"
        @click="updateReply()"
        v-if="update"
        >등록</b-button
      ><b-button
        type="button"
        variant="primary"
        class="m-1"
        @click="deleteReply(replyno)"
        v-if="con"
        >삭제</b-button
      ><b-button
        type="button"
        variant="primary"
        class="m-1"
        v-if="update"
        @click="updateForm()"
        >취소</b-button
      ></b-td
    >
  </b-tr>
</template>

<script>
import { deleteReply, updateReply } from "@/api/reply.js";
export default {
  name: "ReplyListRow",
  data() {
    return {
      update: false,
      con: true,
      replyContent: "",
    };
  },
  created() {
    this.replyContent = this.content;
  },
  props: {
    replyno: Number,
    userid: String,
    username: String,
    content: String,
    boardid: Number,
    regtime: String,
  },
  methods: {
    deleteReply(replyno) {
      if (confirm("정말로 삭제?")) {
        deleteReply(replyno, () => {
          alert("삭제 완료");
          location.reload();
        });
      }
    },
    updateForm() {
      this.update = !this.update;
      this.con = !this.con;
      this.content = this.replyContent;
    },
    updateReply() {
      updateReply(
        {
          replyno: this.replyno,
          userid: this.userid,
          username: this.username,
          content: this.content,
          boardid: this.boardid,
          regtime: this.regtime,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다";
          }
          alert(msg);
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
