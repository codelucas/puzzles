__author__ = 'Lucas'

# This implementation uses seperate chaining for a hash table
# it accepts strings as keys
class HashTable:
    back_bone = None
    size = None

    def __init__(self, size):
        self.back_bone = [[] for x in range(size)]
        self.size = len(self.back_bone)

    def hash(self, key):
        acc = 0
        for i, char in enumerate(key):
            acc += ord(char) * i # ord() converts a character into a unique number

        normalized = acc % self.size
        return normalized

    def put(self, key=None, val=None):
        index = self.hash(key)
        self.back_bone[index].append((key, val))
        return index

    def get(self, key=None):
        index = self.hash(key)
        chain = self.back_bone[index]
        for pair in chain:
            if pair[0] == key:
                return pair[1]
        return None

    def __str__(self):
        string = ""
        for chain in self.back_bone:
            for pair in chain:
                string += str(pair) + " "
            string += "\n"
        return string

def main():
    h = HashTable(10)
    h.put("lucas", 777)
    h.put("jim 123", "lucasdsfdafa")
    h.put("evan", [";olol"])

    print "lucas:", h.get("lucas")
    print "jim 123:", h.get("jim 123")
    print "Failed:", h.get("dsaf")
    print "Evan:", h.get("evan")
    print "String:", h

if __name__ == "__main__":
    main()