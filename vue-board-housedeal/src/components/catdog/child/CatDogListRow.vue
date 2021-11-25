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
  <b-container fluid class="p-4">
    <b-row>
      <b-col>
        <router-link
          :to="{ name: 'CatDogView', params: { articleno: articleno } }"
        >
          {{ articleno }}번 게시물입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!<br />제목:{{
            subject
          }}
        </router-link>
        <br />

        <!-- {{ fileInfos[0].originFile }}<br />
        {{ fileInfos[0].saveFolder }}<br />
        {{ fileInfos[0].saveFile }} -->
        <b-img
          v-if="fileInfos"
          thumbnail
          fluid
          :src="
            require(`D:/git/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${fileInfos[0].saveFolder}/${fileInfos[0].saveFile}`)
          "
        />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";

export default {
  name: "CatDogListRow",
  data() {
    return {
      fileurl: this.saveFile,
      arno: null,
    };
  },
  mounted() {
    console.log("리스트 로우에서 호출하는 articleno: ", this.articleno);
    console.log("fileInfos: ", this.fileInfos);
  },
  props: {
    articleno: Number,
    subject: String,
    //content: String,

    fileInfos: {
      saveFolder: String,
      saveFile: String,
      originFile: String,
    },
  },
  // computed: {
  //   changeDateFormat() {
  //     return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
  // methods: {
  //   resolve_img_url: function (path) {
  //     let images = require.context("../assets/", false, /\.png$|\.jpg$/);
  //     return images("./" + path);
  //   },
  // },
};
</script>

<style></style>
