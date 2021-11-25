<template>
  <b-container class="bv-example-row mt-3 text-center" fluid="xl">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> HAPPY HOUSE
    </h3>
    <b-row>
      <b-col>
        <b-carousel
          controls
          indicators
          v-model="slide"
          :interval="2500"
          background="#e1e1e1"
          img-width="1000"
          img-height="500"
          @sliding-end="endSlide"
          @sliding-start="initCarousel"
          style="text-shadow: 2px 3px 3px #222222"
        >
          <b-carousel-slide
            text="A journey of a thousands mile starts with a single step."
            img-src="https://picsum.photos/1000/500/?image=55"
            caption="The Alchemist"
          >
          </b-carousel-slide>

          <b-carousel-slide img-src="https://picsum.photos/1000/500/?image=56">
            <h2>Love For All, Hatred For None</h2>
          </b-carousel-slide>

          <b-carousel-slide
            img-src="https://picsum.photos/1000/500/?image=57"
          ></b-carousel-slide>

          <b-carousel-slide>
            <template v-slot:img>
              <img
                class="d-block img-fluid w-100"
                src="https://picsum.photos/1000/500/?image=55"
                width="1000"
                height="500"
                alt="Atomic Habits"
              />
            </template>
          </b-carousel-slide>

          <b-carousel-slide caption="Life Quote" img-alt="Life Quote" img-blank>
            <p>The last mile is always the least crowded.</p>
          </b-carousel-slide>
        </b-carousel>
      </b-col>
    </b-row>
    <b-row align-h="around">
      <b-col cols="6" class="float right">
        <news-list></news-list>
      </b-col>
      <b-col cols="6">
        <board-list></board-list>
      </b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>

<script>
import NewsList from "../components/news/NewsList.vue";
import BoardList from "../components/board/BoardList.vue";
import { listArticle } from "@/api/catdog.js";

export default {
  name: "Home",
  components: {
    NewsList,
    BoardList,
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        console.log("홈에서 보는 캣독 리스트 데이터", response.data);
        this.articles = response.data.articles;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  props: {
    msg: String,
  },
  methods: {
    initCarousel() {
      this.sliding = true;
    },
    endSlide() {
      this.sliding = false;
    },
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
