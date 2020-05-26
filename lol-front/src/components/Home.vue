<template>
<div class="page-has-left-panels">
<div id="mypreloader">
<loading></loading>
</div>
<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<!-- Main Header Badges -->

<div class="main-header">
	<div class="content-bg-wrap bg-badges"></div>
	<div class="container">
		<div class="row">
			<div class="col col-lg-8 m-auto col-md-8 col-sm-12 col-12">
				<div class="main-header-content">
					<h1>Welcome to the LolSearcher</h1>
					<p>You can search whoever you want here! And also find some Lol Guide here</p>
				</div>
			</div>
		</div>
	</div>

	<img class="img-bottom" src="@/assets/img/badges-bottom.png" alt="friends">
</div>

<!-- ... end Main Header Badges -->

</div>

</template>


<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'


export default {
  name: 'home',
  data () {
    return {
    }
  },
  components:{
	headline,
	loading
  },
  methods:{
	picFadeout(){
		setTimeout(function () {
			$('#mypreloader').fadeOut(600);
		}, 1500)
	}
  },
  mounted(){
	this.picFadeout()
  }
}

</script>

