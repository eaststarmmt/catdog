<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="8" class="align-self-center">
      [{{ house.dongCode }}] {{ house.aptName }}
    </b-col>
  </b-row>
</template>

<script>
// import KakaoMap from "@/components/house/KakaoMap.vue";
import { mapActions } from "vuex";
export default {
  name: "HouseListRow",
  components: {
    // KakaoMap,
  },
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(["detailMap"]),
    colorChange(flag) {
      this.isColor = flag;
    },
    selectHouse() {
      this.detailMap(this.house);
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
