<template>
  <div id="group-introduction">
    <router-link to="/GroupIntroduce">部门介绍</router-link>
  </div>
</template>
<style scoped>
#group-introduction a:hover {
  background: url(../assets/group_pic/group.gif) center;
  border-radius: 25px;
}
#group-introduction a {
  display: block;
  height: 100%;
  text-decoration: none;
  line-height: 500px;
}
#group-introduction {
  height: 500px;
  border-radius: 8px;
  display: block;
  margin-bottom: 20px;
  background-color: royalblue;
  font-size: 4em;
}
</style>
