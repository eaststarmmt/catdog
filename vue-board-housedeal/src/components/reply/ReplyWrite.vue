<template>
  <div>
    <h2>댓글 작성</h2>
    <!-- <b-form @submit="onSubmit" @reset="onReset"> -->
    <b-form-group
      id="userid-group"
      label="작성자:"
      label-for="userid"
      description="작성자를 입력하세요."
    >
      <b-form-input
        id="userid"
        v-model="userInfo.userid"
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
      ></b-form-textarea>
    </b-form-group>

    <b-button type="submit" variant="primary" class="m-1">글작성</b-button>
    <b-button type="submit" variant="primary" class="m-1">글수정</b-button>
    <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
    <!-- </b-form> -->
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "ReplyWrite",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
