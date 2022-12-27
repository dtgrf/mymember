import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/yxUser',
    method: 'post',
    data
  })
}

export function del(uid) {
  return request({
    url: 'api/yxUser/' + uid,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/yxUser',
    method: 'put',
    data
  })
}

export function onStatus(id, data) {
  return request({
    url: 'api/yxUser/onStatus/' + id,
    method: 'post',
    data
  })
}
export function getAll() {
  return request({
    url: 'api/yxUser/getAllList',
    method: 'get'
  })
}
export function getBirthUser() {
  return request({
    url: 'api/yxUser/getBirthList',
    method: 'get'
  })
}
export function editp(data) {
  return request({
    url: 'api/yxUser/money',
    method: 'post',
    data
  })
}
