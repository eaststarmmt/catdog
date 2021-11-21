<template>
  <b-table-simple>
    <tbody>
      <b-tr v-for="(reply, index) in replies" :key="index">
        <b-td>{{ reply.username }}</b-td>
        <b-td>{{ reply.content }}</b-td>
        <b-td
          ><b-button type="button" variant="primary" class="m-1"
            >글수정</b-button
          ></b-td
        >
      </b-tr>
    </tbody>
  </b-table-simple>
</template>

<script>
import { listReply } from "@/api/reply.js";

export default {
  name: "ReplyList",
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
};
</script>

<style></style>
