<template>
  <!-- <b-tr> -->
  <!-- <b-td>{{ articleno }}</b-td> -->
  <!-- <b-th class="text-left">
      <router-link
        :to="{ name: 'CatDogView', params: { articleno: articleno } }"
        >{{ saveFile }}</router-link
      >
    </b-th> -->
  <!-- <b-td>{{ saveFile }}</b-td> -->
  <!-- </b-tr> -->
  <b-container fluid class="p-4 bg-dark">
    <b-row>
      <b-col>
        <router-link
          :to="{ name: 'CatDogView', params: { articleno: this.articleno } }"
          ><b-img thumbnail fluid :src="`@/assets/211124/${fileurl}`" />
        </router-link>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";

export default {
  name: "CatDogListRow",
  data() {
    return {
      fileurl: this.saveFile,
    };
  },
  mounted() {
    console.log(this.fileurl);
  },
  props: {
    articleno: Number,
    safeFolder: String,
    originFile: String,
    saveFile: String,
  },
  computed: {
    changeDateFormat() {
      return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
  // methods: {
  //   resolve_img_url: function (path) {
  //     let images = require.context("../assets/", false, /\.png$|\.jpg$/);
  //     return images("./" + path);
  //   },
  // },
};
</script>

<style></style>
