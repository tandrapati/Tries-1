import java.util.List;

class Solution {
    Trie trie;
    public String replaceWords(List<String> dictionary, String sentence) {
        this.trie = new Trie();
        for(String word : dictionary) {
            trie.insert(word);
        }
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(String word : words) {
            result.append(getRoot(word));
            result.append(" ");
        }
        return result.toString().trim();
    }
    private String getRoot(String word) {
        StringBuilder sb = new StringBuilder();
        for (char ch : word.toCharArray()) {
            sb.append(ch);
            if (!trie.startsWith(sb.toString()) ) {
                return word;
            }
            else if(trie.search(sb.toString())) {
                return sb.toString();
            }
        }
        return word;
    }
    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.nodes[ch-'a'] == null) {
                    curr.nodes[ch-'a'] = new TrieNode();
                }
                curr = curr.nodes[ch-'a'];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.nodes[ch-'a'] == null) {
                    return false;
                }
                curr = curr.nodes[ch-'a'];
            }
            return curr.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for(char ch : prefix.toCharArray()) {
                if(curr.nodes[ch-'a'] == null) {
                    return false;
                }
                curr = curr.nodes[ch-'a'];
            }
            return true;
        }

        class TrieNode{
            boolean isEnd;
            TrieNode[] nodes;
            public TrieNode() {
                isEnd = false;
                nodes = new TrieNode[26];
            }
        }
    }
}