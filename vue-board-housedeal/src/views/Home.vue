<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> HAPPY HOUSE
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <kakao-map-api></kakao-map-api>
      </b-col>
      <b-col></b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="9">
        <news-list></news-list>
      </b-col>
      <b-col></b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>

<script>
import NewsList from "../components/news/NewsList.vue";
import KakaoMapApi from "@/components/map/KakaoMapApi.vue";

export default {
  name: "Home",
  components: {
    NewsList,
    KakaoMapApi,
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
