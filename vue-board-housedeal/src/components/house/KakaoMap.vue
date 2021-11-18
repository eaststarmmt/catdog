<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
// 카카오 맵에서 vuex에 있는 데이터를 가지고 와야함
import { mapActions } from "vuex";

export default {
  name: "KakaoMap",
  // house로 데이터는 불러왔는데 이제 이거를 어떻게 해야하냐
  data() {
    return {
      map: null,
      markers: [],
      infowindow: null,
      customOverlay: null,
    };
  },
  props: {
    house: Object,
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );
      console.log("positions가 뭐임", positions);

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    addMarker(position, idx) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      this.marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(this.marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      // var content = `
      //   <div class="overlaybox">
      //     <div class="boxtitle">${title0}</div>
      //     <div class="first"><img src="${root}./img/apt.png" style="width:247px; height:136px;" alt=""></div>
      //     <ul>
      //       <li class="up">
      //         <span class="title0">건축년도</span>
      //         <span class="count">${place.buildYear}</span>
      //       </li>
      //       <li>
      //         <span class="title0">주소</span>
      //         <span class="count">${place.sidoName} ${place.gugunName} ${place.dongName} ${place.jibun}</span>
      //       </li>
      //       <li>
      //         <span class="title0">최신거래금액</span>
      //         <span class="count">${place.recentPrice}</span>
      //       </li>
      //       <li>
      //         <span class="last" id="recenthistor" data-toggle="modal" data-target="#myModal">아파트정보 update</span>
      //       </li>
      //     </ul>
      //   </div>
      // `;
      var position = new kakao.maps.LatLng(
        this.marker.getPosition().getLat() + 0.00033,
        this.marker.getPosition().getLng() - 0.00003
      );
      this.customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: this.content,
        xAnchor: 0.3,
        yAnchor: 0.91,
      });
      this.customOverlay.setMap(this.map);

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
