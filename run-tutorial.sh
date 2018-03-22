#!/bin/bash

export LEIN_FAST_TRAMPOLINE=y

lein run -m clojure.main -e "(require 'user)" -e "(user/start-figwheel '[tutorial])"
