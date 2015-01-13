#!/usr/bin/python3
"""
This file, and others named similarly to it, contain
snippets of me converting code from imperative to
functional for practice.
"""

import copy


# Imperative example

def get_bands():
    return [{'name': 'sunset rubdown', 'country': 'UK', 'active': False},
            {'name': 'women', 'country': 'Germany', 'active': False},
            {'name': 'a silver mt. zion', 'country': 'Spain', 'active': True}]


def format_bands(bands):
    for band in bands:
        band['country'] = 'Canada'
        band['name'] = band['name'].replace('.', '')
        band['name'] = band['name'].title()

bands = get_bands()
format_bands(bands)
print(bands)

# => [{'name': 'Sunset Rubdown', 'active': False, 'country': 'Canada'},
#     {'name': 'Women', 'active': False, 'country': 'Canada' },
#     {'name': 'A Silver Mt Zion', 'active': True, 'country': 'Canada'}]


# Functional conversion

def pipeline(data, fns):
    return reduce(lambda _data, fn: map(fn, _data),
                  fns,
                  data)


def set_record(data, key, value):
    data = copy.deepcopy(data)
    data[key] = value
    return data


def call(fn, k):
    def iter_call(data):
        return set_record(data, k, fn(data[k]))
    return iter_call


def keep_fields(keys):
    def iter_call(data):
        return reduce(lambda a, x: set_record(a, x, data[x]),
                      keys, {})
    return iter_call


bands = get_bands()
new_ans = pipeline(bands, [
    call(lambda x: 'Canada', 'country'),
    call(lambda x: x.replace('.', ''), 'name'),
    call(lambda x: x.title(), 'name'),
    keep_fields(['name', 'country'])
])

print '--'
print new_ans
