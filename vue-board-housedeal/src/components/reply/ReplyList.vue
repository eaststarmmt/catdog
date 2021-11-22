<template>
  <b-table-simple>
    <tbody>
      <reply-list-row
        v-for="(reply, index) in replies"
        :key="index"
        v-bind="reply"
      />
      <!-- <b-tr v-for="(reply, index) in replies" :key="index">
        <b-td>{{ reply.username }}</b-td>
        <b-td>{{ reply.content }}</b-td>
        <b-td
          ><b-button type="button" variant="primary" class="m-1"
            >수정</b-button
          ></b-td
        >
        <b-td
          ><b-button
            type="button"
            variant="primary"
            class="m-1"
            @click="deleteReply(reply.replyno)"
            >삭제</b-button
          ></b-td
        >
      </b-tr> -->
    </tbody>
  </b-table-simple>
</template>

<script>
import { listReply, deleteReply } from "@/api/reply.js";
import ReplyListRow from "@/components/reply/child/ReplyListRow";
export default {
  name: "ReplyList",
  components: {
    ReplyListRow,
  },
  data() {
    return {
      replies: [],
    };
  },
  props: {
    articleno: Number,
  },
  created() {
    listReply(
      this.$route.params.articleno,
      (response) => {
        this.replies = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
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
  },
};
</script>

<style></style>
