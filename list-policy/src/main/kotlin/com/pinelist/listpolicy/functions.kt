package com.pinelist.listpolicy

import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.railway.TwoTrack

typealias FindList = (TwoTrack<String>) -> TwoTrack<Pinelist>
typealias FindAll = () -> TwoTrack<List<Pinelist>>
typealias CreateList = (TwoTrack<String>) -> TwoTrack<Pinelist>
typealias AddItem = (TwoTrack<AddItemRequest>) -> TwoTrack<Pinelist>
