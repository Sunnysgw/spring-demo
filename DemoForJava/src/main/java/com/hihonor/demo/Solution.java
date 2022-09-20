package com.hihonor.demo;

import java.util.*;

class Solution {

    class Keys {

        Map<Character, Integer> map = new HashMap<>();

        public Keys(String str) {
            if (null != str && !str.isEmpty()) {
                for (int i = 0; i < str.length(); i++) {
                    char item = str.charAt(i);
                    map.put(item, map.getOrDefault(item, 0) + 1);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Keys keys = (Keys) o;
            if (map.size() != keys.map.size()) {
                return false;
            }
            for(Map.Entry<Character, Integer> item : map.entrySet()) {
                Character k = item.getKey();
                Integer v = item.getValue();
                if (!keys.map.containsKey(k) || !Objects.equals(keys.map.get(k), v)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;
            for (Map.Entry<Character, Integer> item : map.entrySet()) {
                Character k = item.getKey();
                Integer v = item.getValue();
                hashCode += k;
                hashCode += v;
            }
            return hashCode;
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Keys, List<String>> output = new HashMap<>();
        for(String str : strs) {
            Keys k = new Keys(str);
            List<String> v = output.getOrDefault(k, new ArrayList<>());
            v.add(str);
            output.put(k, v);
        }
        return new ArrayList<>(output.values());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        solution.groupAnagrams(strs);
    }

}