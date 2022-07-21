// 查询列表接口
const getIntelligencePage = (params) => {
  return $axios({
    url: '/myIntelligence/page',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// （批量）添加情报至协同任务
const addCoTask = (params) => {
  return $axios({
    url: '/myIntelligence/coTask/',
    method: 'post',
    //注意这里传递的是myIntelligence的主键id
    params: { ids: params.id }
  })
}

const queryFileHash = (row) => {
  return $axios({
    url: `/myIntelligence/hash`,
    method: 'post',
    data :  row
  })
}
