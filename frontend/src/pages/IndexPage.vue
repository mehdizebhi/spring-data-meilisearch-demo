<template>
  <q-page>

    <div class="row flex-center q-my-md">
      <div class="col-xs-2 col-sm-1 col-md-1 q-mx-md">
        <q-select v-model="size" :options="sizeList" label="Movie per page" />
      </div>
      <div class="col-xs-8 col-sm-6 col-md-4">
        <q-input
          v-model="search"
          debounce="500"
          filled
          placeholder="Search"
        >
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </div>
    </div>

    <div class="row flex-center items-start q-gutter-md q-py-md q-px-xl">

      <q-card v-for="movie in movies" :key="movie.id" class="my-card" flat bordered>
        <q-img
          :src="movie.poster"
        />

        <q-card-section>
          <div class="text-overline text-orange-9">{{ new Date(movie.release_date * 1000).toISOString().slice(0, 10) }}</div>
          <div class="text-h5 q-mt-sm q-mb-xs">{{ movie.title }}</div>
          <div class="text-caption text-grey">
            {{ movie.overview }}
          </div>
        </q-card-section>

        <q-card-actions>
          <q-btn  v-for="(genre, index) in movie.genres" :key="`genre-${index}`" flat color="warning" :label="genre" />
          <q-space />
        </q-card-actions>

      </q-card>
    </div>
    <div class="row flex-center q-mb-lg">
      <q-pagination
        class="justify-center"
        v-model="page"
        :max="totalPages"
        :max-pages="6"
        boundary-numbers
        direction-links
      />
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'IndexPage',
  data () {
    return {
      search: "",
      page: 1,
      size: 12,
      totalPages: 1,
      totalElements: 0,
      sizeList: [12, 24, 54, 99],
      movies: []
    }
  },
  methods: {
    async setMovies(search, page, size) {
      await this.$api.getMovies(search, page - 1, size)
        .then((response) => {
          this.movies = response.data.content;
          this.totalPages = response.data.metadata.totalPages;
          this.totalElements = response.data.metadata.totalElements;
        })
    }
  },
  watch: {
    async search(newSearch) {
      this.page = 1;
      await this.setMovies(newSearch, this.page, this.size);
    },
    async page(newPage) {
      await this.setMovies(this.search, newPage, this.size);
    },
    async size(newSize) {
      await this.setMovies(this.search, this.page , newSize);
    }
  },
  async mounted() {
    await this.setMovies(this.search, 1, this.size);
  }
});
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 350px
</style>
