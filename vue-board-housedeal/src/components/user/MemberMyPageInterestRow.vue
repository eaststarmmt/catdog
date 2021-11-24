<template>
  <b-container style="text-align: left">
    {{ codeToString }}

    <button v-if="this.isin == 'apt'" @click.prevent="showInterest">
      보기
    </button>
    <button v-if="this.isin == 'mod'" @click.prevent="deleteInterest">
      삭제
    </button>
  </b-container>
</template>

<script>
import { convertCodeToString } from "../../api/member.js";
import { mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "MemberMyPagInterestRow",
  data() {
    return {
      codeToString: "",
    };
  },
  props: {
    area: String,
    isin: String,
  },
  created() {
    this.convertInterCodeToString();
  },
  methods: {
    ...mapActions(houseStore, ["getDBHouseList"]),
    ...mapMutations(memberStore, ["DELETE_AREA"]),
    //동코드를 글자로
    convertInterCodeToString() {
      //console.log(this.area);
      convertCodeToString(this.area, (response) => {
        this.codeToString = response.data;
      });
    },
    showInterest() {
      console.log("현재", this.area);
      this.getDBHouseList(this.area);
    },
    deleteInterest() {
      this.DELETE_AREA(this.area);
    },
  },
};
</script>

<style></style>
