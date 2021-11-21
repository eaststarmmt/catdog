<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="instagram"></b-icon> 캣독그램 Service
    </h3>
    <b-button
      variant="outline-dark"
      class="btn float-right"
      @click="moveRegister()"
      >등록</b-button
    >
    <!-- 등록버튼 누르면 입력화면 창으로 이동 -->
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <template #header>SSAFY 캣독그램</template>

          <hr class="my-4" />
          <!-- 여기 아래부터 이미지 불러와서 업로드하기 -->
          <b-container fluid class="p-4 bg-dark">
            <b-row>
              <b-col>
                <b-img
                  thumbnail
                  fluid
                  src="https://picsum.photos/250/250/?image=54"
                  alt="Image 1"
                ></b-img>
              </b-col>
              <b-col>
                <b-img
                  thumbnail
                  fluid
                  src="https://picsum.photos/250/250/?image=58"
                  alt="Image 2"
                ></b-img>
              </b-col>
              <b-col>
                <b-img
                  thumbnail
                  fluid
                  src="https://picsum.photos/250/250/?image=59"
                  alt="Image 3"
                ></b-img>
              </b-col>
            </b-row>
          </b-container>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Catdoggram",
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
</style>
